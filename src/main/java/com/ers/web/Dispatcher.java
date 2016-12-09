package com.ers.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String requestURI = req.getRequestURI();
		switch(requestURI){
			case"/ers/":{
				resp.sendRedirect("login.jsp");
			}
			case "/ers/login.do":{
				LoginController.getInstance().login(req, resp);
			}
			case"/ers/processLogin.do":{
				System.out.println("Works!!");
			}
			default:{
				resp.setStatus(404);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
