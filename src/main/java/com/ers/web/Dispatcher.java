package com.ers.web;

import java.io.IOException;

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
			case "/ers/login.do":{
				LoginController.getInstance().login(req, resp);
				break;
			}
			case "/ers/secure/logout.do": {
				LoginController.getInstance().logout(req, resp);
				break;
			}
			case "/ers/secure/main.do":{
				MainController.getInstance().getUserData(req, resp);
				break;
			}
			case "/ers/secure/createReim.do":{
				MainController.getInstance().createReim(req, resp);
				break;
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
