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
			session.setAttribute("currentSelection", "All");

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
	
	/***************** Status ******************/
	public void getAccepted(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByStatus(user, "Accepted");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Accepted");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}

	public void getDenied(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByStatus(user, "Denied");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Denied");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}

	public void getPending(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByStatus(user, "Pending");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Pending");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}

	public void getLodging(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByType(user, "Lodging");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Lodging");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
	
	public void getFood(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByType(user, "Food");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Food");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
	
	public void getTravel(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByType(user, "Travel");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Travel");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
	
	public void getOther(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");

		try {
			List<Reim> list = BusinessFactory.getDelegate().getReimByType(user, "Other");
			session.setAttribute("reims", list);
			session.setAttribute("currentSelection", "Other");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}

}
