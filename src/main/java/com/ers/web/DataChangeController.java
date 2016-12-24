package com.ers.web;

import java.io.IOException;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.business.BusinessFactory;
import com.ers.exception.UnauthorizedException;
import com.ers.exception.ValidateException;

public class DataChangeController {

	private static DataChangeController INSTANCE;
	
	private DataChangeController(){
		super();
	}
	
	synchronized public static DataChangeController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new DataChangeController();
		return INSTANCE;
	}
	
	//TODO: Move to create controller
		private double validateAmount(String amountString)
				throws ValidateException{
			double amount;
			try{
				amount = Double.valueOf(amountString);
				if(amount > 0){
					int decimalPoint = amountString.indexOf('.');
					int numDecimals = amountString.length() - decimalPoint -1;
					if(numDecimals > 2){
						String message = "Reimbursement was not added. Please input a valid number.";
						throw new ValidateException(message);
					}
				}
				return amount;
			}catch(NumberFormatException e){
				String message = "Reimbursement was not added. Please input a valid number.";
				throw new ValidateException(message);
			}
		}

		private void validateDescription(String description) 
				throws ValidateException{
			if(description == null || description.length() > 250){
				String message = "Please input a description that is less than 250 characters";
				throw new ValidateException(message);
			}
		}

		private Type validateType(List<Type> list, String typeSelected) 
				throws ValidateException{
			int typeId = 0;
			for(Type type: list){
				if(type.getType().equals(typeSelected))
					typeId = type.getId();
			}
			if(typeId == 0){
				String message = "Reimbursement was not added. Please input a valid type.";
				throw new ValidateException(message);
			}
			return new Type(typeId, typeSelected);
		}

		private Status validateStatus(List<Status> list, String statusSelected) 
				throws ValidateException{
			int statusId = 0;
			for(Status status: list){
				if(status.getStatus().equals(statusSelected)){
					statusId = status.getId();
				}
			}
			if(statusId == 0){
				String message = "Reimbursement cannot be added at this time. "
						+ "Please try again later.";
				throw new ValidateException(message);
			}
			return new Status(statusId, statusSelected);
		}

		public void createReim(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException{

			// grab session
			HttpSession session = req.getSession();
			try{
				// validate amont
				double amount = validateAmount(req.getParameter("amount"));

				// validate type
				@SuppressWarnings("unchecked")
				List<Type> typeList = (List<Type>)session.getAttribute("types");
				Type type = validateType(typeList, req.getParameter("type"));

				// validate status
				@SuppressWarnings("unchecked")
				List<Status> statusList = (List<Status>) session.getAttribute("status");
				Status status = validateStatus(statusList, "Pending");

				// validate description
				String description = req.getParameter("description");
				validateDescription(description);

				User user = (User) session.getAttribute("user");

				try{
					// add reim
					Reim newReim = BusinessFactory.getDelegate().createReim(user, amount, type, status, description);
					@SuppressWarnings("unchecked")
					List<Reim> reimList = (List<Reim>) session.getAttribute("reims");
					// TODO sort check
					reimList.add(newReim);
					session.setAttribute("reims", reimList);

					// send confirmation to client
					String message = createClientMessage("Reimbursement:",newReim.getDescription(),"was successfully created");
					req.setAttribute("successMessage", message);
					req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
					.forward(req, resp);
				}catch(ServiceUnavailableException e){
					e.printStackTrace();
				}
			}catch(ValidateException e){
				e.printStackTrace();
				req.setAttribute("errorMessage", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}
		}
		
		// Ilya helped
		private String createClientMessage(String prefix, String fullDescription ,String postfix){
			int dLength = fullDescription.length();
			int maxLength = 28, maxWord = 10;
			int index = dLength > maxLength ? maxLength : dLength;
			int nextSpace = fullDescription.indexOf(' ', index);
			if(nextSpace > index && nextSpace - index <= maxWord ){
				index = nextSpace;
			}
			if(dLength - index < 7) index = dLength;
			String dotString = index < dLength ? "..." : ""; 
			return prefix + " \"" + fullDescription.substring(0, index) 
				+ dotString + "\" " + postfix + ".";
			
		}
		
		
		public Reim validateReim(List<Reim> reimList, int reimSelected) 
				throws ValidateException{
			Reim testReim = new Reim();
			testReim.setId(reimSelected);
			if(!reimList.contains(testReim)){
				String message = "Please pick a reimbursement that is valid for approval.";
				throw new ValidateException(message);
			}
			int location = reimList.indexOf(testReim);
			
			// checks if reim is already resolved
			if(reimList.get(location).getResolved() != null){
				String message = "Please pick a reimbursement that is valid for approval.";
				throw new ValidateException(message);
			}
			return reimList.get(location);
		}

		public void acceptReim(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException{
			HttpSession session = req.getSession();

			int reimId = Integer.parseInt(req.getParameter("acceptReim"));

			System.out.println("Selected Reim to update: " + reimId);

			@SuppressWarnings("unchecked")
			List<Reim> reimList = (List<Reim>)session.getAttribute("reims");

			try{
				// validate reim 
				Reim reim = validateReim(reimList, reimId);
				System.out.println("Reim validated for update: " + reim);

				// validate status
				@SuppressWarnings("unchecked")
				List<Status> statusList = (List<Status>) session.getAttribute("status");		
				Status status = validateStatus(statusList, "Accepted");


				// grab user
				User user = (User) session.getAttribute("user");

				// change status
				BusinessFactory.getDelegate().changeStatus(reim, user, status);

				// send confrimation to client
				String message = createClientMessage("Updated reimbursement:",reim.getDescription(),"to " 
						+ status.getStatus().toLowerCase());
				req.setAttribute("successMessage", message);
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}catch(ValidateException e){
				e.printStackTrace();
				req.setAttribute("errorMessage", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}catch(ServiceUnavailableException e){
				e.printStackTrace();
				req.setAttribute("errorMessage", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}catch(UnauthorizedException e){
				e.printStackTrace();
				req.setAttribute("errorMessage", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}
		}

		public void denyReim(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
			System.out.println("Deny Reim: " + req.getParameter("denyReim"));
			HttpSession session = req.getSession();

			int reimId = Integer.parseInt(req.getParameter("denyReim"));

			System.out.println("Selected Reim to update: " + reimId);

			@SuppressWarnings("unchecked")
			List<Reim> reimList = (List<Reim>)session.getAttribute("reims");

			try{
				// validate reim 
				Reim reim = validateReim(reimList, reimId);
				System.out.println("Reim validated for update: " + reim);

				// validate status
				@SuppressWarnings("unchecked")
				List<Status> statusList = (List<Status>) session.getAttribute("status");		
				Status status = validateStatus(statusList, "Denied");


				// grab user
				User user = (User) session.getAttribute("user");

				// change status
				BusinessFactory.getDelegate().changeStatus(reim, user, status);

				// send confrimation to client
				String message = createClientMessage("Updated reimbursement:",reim.getDescription(),"to " 
						+ status.getStatus().toLowerCase());
				req.setAttribute("errorMessage", message);
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}catch(ValidateException e){
				e.printStackTrace();
				req.setAttribute("errorMessage", e.getMessage());
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}catch(ServiceUnavailableException e){
				e.printStackTrace();
				String message = "Reimbursement cannot be added at this time. "
						+ "Please try again later.";
				req.setAttribute("errorMessage", message);
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}catch(UnauthorizedException e){
				e.printStackTrace();
				String message = "You do do not have enough permission to"
						+ " accept Reimbursements";
				req.setAttribute("errorMessage", message);
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
			}		
		}
}
