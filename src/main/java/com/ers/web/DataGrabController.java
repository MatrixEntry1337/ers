package com.ers.web;

import java.io.IOException;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.Messaging.SyncScopeHelper;

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
			session.setAttribute("currentSort", "All");

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
			session.setAttribute("currentSort", "Accepted");
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
			session.setAttribute("currentSort", "Denied");
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
			session.setAttribute("currentSort", "Pending");
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
			session.setAttribute("currentSort", "Lodging");
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
			session.setAttribute("currentSort", "Food");
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
			session.setAttribute("currentSort", "Travel");
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
			session.setAttribute("currentSort", "Other");
			req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
			.forward(req, resp);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			resp.sendError(500);
		}
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

		BusinessFactory.getDelegate().getDateDescend(original);

		session.setAttribute("currentSort", 6);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

}
