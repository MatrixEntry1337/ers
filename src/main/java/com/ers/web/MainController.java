package com.ers.web;

import java.io.IOException;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.beans.Reim;
import com.ers.beans.User;
import com.ers.business.BusinessDelegate;

public class MainController{

	private static MainController INSTANCE;

	private MainController(){}

	synchronized public static MainController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new MainController();
		return INSTANCE;
	}

	private HttpSession checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null)
			resp.sendError(401);
		return session;
	}

	public void getUserData(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = checkLogin(req, resp);
		User user = (User)session.getAttribute("user");
		try{
			List<Reim> list = BusinessDelegate.getInstance().getReims(user);
			session.setAttribute("reims", list);
			req.getRequestDispatcher("WEB-INF/pages/main.jsp")
			.forward(req, resp);
		}catch(ServiceUnavailableException e){
			e.printStackTrace();
			resp.sendError(500);
		}

	}

	//TODO: Move to create controller
	private boolean validateData(HttpServletRequest req, HttpServletResponse resp){	
		double amount = Double.valueOf(req.getParameter("amount")); 
		if(amount > 0){
			String amountString = Double.toString(amount);
			int decimalPoint = amountString.indexOf('.');
			int numDecimals = amountString.length() - decimalPoint -1;
			if(numDecimals > 2){
				return false;
			}

		}
		
		String type = req.getParameter("type");
		//TODO Check for type
		
		//TODO Check the length of the description
		return true;
			
	}

	public void createReim(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{


	}
}
