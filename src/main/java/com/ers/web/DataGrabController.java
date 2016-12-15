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
}
