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
import com.ers.business.BusinessDelegate;
import com.ers.business.BusinessFactory;
import com.ers.exception.ReimCreationException;

public class MainController{

	private static MainController INSTANCE;

	private MainController(){}

	synchronized public static MainController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new MainController();
		return INSTANCE;
	}

	public void getUserData(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		try{
			List<Reim> reimList = BusinessFactory.getDelegate().getReims(user);
			session.setAttribute("reims", reimList);
			
			if(session.getAttribute("types") == null){
				// grab data
				System.out.println("Grabbed Data and created Sessions.");
				List<Type> typeList = BusinessFactory.getDelegate().getAllTypes();
				List<Status> statusList = BusinessFactory.getDelegate().getAllStatus();
				// set session data
				session.setAttribute("types", typeList);
				session.setAttribute("status", statusList);
			}
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		}catch(ServiceUnavailableException e){
			e.printStackTrace();
			resp.sendError(500);
		}

	}

	//TODO: Move to create controller
	private double validateAmount(String amountString)
			throws ReimCreationException{
		double amount;
		try{
			amount = Double.valueOf(amountString);
			if(amount > 0){
				int decimalPoint = amountString.indexOf('.');
				int numDecimals = amountString.length() - decimalPoint -1;
				if(numDecimals > 2){
					throw new ReimCreationException();
				}
			}
			return amount;
		}catch(NumberFormatException e){
			throw new ReimCreationException();
		}
	}

	private Type validateType(List<Type> list, String typeSelected) 
			throws ReimCreationException{
		int typeId = 0;
		for(Type type: list){
			if(type.getType().equals(typeSelected))
				typeId = type.getId();
		}
		if(typeId == 0)
			throw new ReimCreationException();
		return new Type(typeId, typeSelected);
	}
	
	private Status validateStatus(List<Status> list, String statusSelected) 
			throws ReimCreationException{
		int statusId = 0;
		for(Status status: list){
			if(status.getStatus().equals(statusSelected)){
				statusId = status.getId();
			}
		}
		if(statusId == 0)
			throw new ReimCreationException();
		return new Status(statusId, statusSelected);
	}
	
	public void createReim(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		// debug
		System.out.println(req.getParameter("amount"));
		System.out.println(req.getParameter("description"));
		System.out.println(req.getParameter("type"));
		try{
			HttpSession session = req.getSession();
			@SuppressWarnings("unchecked")
			List<Type> typeList = (List<Type>)session.getAttribute("types");
			@SuppressWarnings("unchecked")
			List<Status> statusList = (List<Status>) session.getAttribute("status");

			// check
			double amount = validateAmount(req.getParameter("amount"));
			Type type = validateType(typeList, req.getParameter("type"));
			System.out.println(type);
			Status status = validateStatus(statusList, "Pending");
			System.out.println(status);
			User user = (User) session.getAttribute("user");
			System.out.println(user);
			String description = req.getParameter("description");
			try{
				Reim newReim = BusinessFactory.getDelegate().createReim(user, amount, type, status, description);
				@SuppressWarnings("unchecked")
				List<Reim> reimList = (List<Reim>) session.getAttribute("reims");
				reimList.add(newReim);
				session.setAttribute("reims", reimList);
				String message = "Reimbursment was successfully created.";
				req.setAttribute("successMessage", message);
				req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
					.forward(req, resp);
			}catch(ServiceUnavailableException e){
				e.printStackTrace();
			}
		}catch(ReimCreationException e){
			String message = "Reimbursement was not added. Please input valid arguments.";
			req.setAttribute("errorMessage", message);
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
				.forward(req, resp);
		}
	}
}
