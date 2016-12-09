package com.ers.web;

import java.io.IOException;
import java.util.Date;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ers.beans.User;
import com.ers.service.BusinessFactory;

public class LoginController {
	private static LoginController INSTANCE = null;

	private LoginController(){}

	synchronized public static LoginController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new LoginController();
		return INSTANCE;
	}

	private void logUserData(HttpServletRequest req, User user){
		// creates session if not already created
		HttpSession session = req.getSession(true);

		// set user data
		session.setAttribute("user", user);

		// debug
		if(true){
			User test = (User)session.getAttribute("user");
			System.out.println("User: " + test.getUsername());
			System.out.println("Logged in at: " + new Date().toString());
			System.out.println("User session first created at: " + session.getCreationTime());
			System.out.println("User last logged in at " + session.getLastAccessedTime());	
		}

	}

	public void login(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException{
		String username = req.getParameter("username"); 
		String password = req.getParameter("password");

		if(username != null && password != null){
			username = username.trim();
			password = password.trim();
			if(username.length() == 0 && password.length() == 0){
				req.setAttribute("loginMessage", "Please valid credentials");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
			else{
				// create session
				try{
					User user = BusinessFactory.getDelegate()
							.authenticateUser(req.getParameter("username"), req.getParameter("password"));
					logUserData(req, user);
					// goto next page
					resp.sendRedirect("processLogin.do");

				}catch(AuthenticationException e){
					e.printStackTrace();
					//			resp.sendRedirect("login.jsp");
					req.setAttribute("loginMessage", 
							"There was an error with your Username/Password combination. Please try again.");
					req.getRequestDispatcher("login.jsp").forward(req, resp);

				}catch(ServiceUnavailableException e){
					e.printStackTrace();
				}
			}
		
		}
	}
}