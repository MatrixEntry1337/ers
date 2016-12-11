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
import com.ers.service.BusinessDelegate;

public class MainController{

	private static MainController INSTANCE;

	private MainController(){}

	synchronized public static MainController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new MainController();
		return INSTANCE;
	}

//	private User getSessionUser(HttpServletRequest req, HttpServletResponse resp){
//		HttpSession session = req.getSession(true);
//		User user = (User)session.getAttribute("user");
//		if(user == null)
//			resp.setStatus(401);
//		return user;
//	}
	
	public void getUserData(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null){
			System.out.println("It works");
			resp.sendError(401);
		}
		User user = (User)session.getAttribute("user");
		try{
			List<Reim> list = BusinessDelegate.getInstance().getReims(user);
			session.setAttribute("reims", list);
			req.setAttribute("Reims", list);
			System.out.println(list);
			req.getRequestDispatcher("WEB-INF/pages/main.jsp")
			.forward(req, resp);
		}catch(ServiceUnavailableException e){
			e.printStackTrace();
			resp.setStatus(500);
		}
		
	}
}
