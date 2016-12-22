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

public class DataGrabController{

	private static DataGrabController INSTANCE;

	private DataGrabController(){
		super();
	}

	synchronized public static DataGrabController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new DataGrabController();
		return INSTANCE;
	}

	public void getUserData(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		try{
			List<Reim> reimList = BusinessFactory.getDelegate().getReims(user);
			session.setAttribute("reims", reimList);
			session.setAttribute("currentSort", 1);
			
			// will load types and status only once
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
	
	@SuppressWarnings("unchecked")
	public void getAccepted(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		List<Reim> original = (List<Reim>)session.getAttribute("reims");
		
		session.setAttribute("currentSort", 2);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	public void getDenied(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		List<Reim> original = (List<Reim>)session.getAttribute("reims");
		
		session.setAttribute("currentSort", 3);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	public void getPending(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		List<Reim> original = (List<Reim>)session.getAttribute("reims");
		
		session.setAttribute("currentSort", 4);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	public void getDateAscend(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		List<Reim> original = (List<Reim>)session.getAttribute("reims");
		
		session.setAttribute("currentSort", 5);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}
	
	@SuppressWarnings("unchecked")
	public void getDateDescend(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		List<Reim> original = (List<Reim>)session.getAttribute("reims");
		
		session.setAttribute("currentSort", 6);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}
	
}
