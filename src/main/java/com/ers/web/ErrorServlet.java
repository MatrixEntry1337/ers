package com.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// error handling logic
		// get exception object
//		Exception e = (Exception)
//				req.getAttribute("javax.servlet.error.exception");
		
		// get HTTP status code
		int statusCode = (Integer) 
				req.getAttribute("javax.servlet.error.status_code");
		switch(statusCode){
			case 404:{
				resp.sendRedirect("/ers/404.jsp");
				break;
			}
			case 403:{
				resp.sendRedirect("/ers/403.jsp");
			}
			case 500:{
				resp.sendRedirect("/ers/500.jsp");
			}
			default:{
				System.err.println("Error code: " + statusCode + " was fired");
				resp.sendRedirect("/ers/login.jsp");
			}
		}
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	

}
