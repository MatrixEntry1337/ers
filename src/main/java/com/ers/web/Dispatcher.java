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
				DataGrabController.getInstance().getUserData(req, resp);
				break;
			}
			case "/ers/secure/createReim.do":{
				DataChangeController.getInstance().createReim(req, resp);
				break;
			}
			case "/ers/secure/accept.do":{
				DataChangeController.getInstance().acceptReim(req, resp);
				break;
			}
			case "/ers/secure/deny.do":{
				DataChangeController.getInstance().denyReim(req, resp);
				break;
			}
			case "/ers/secure/accepted.do":{
				break;
			}
			case "/ers/secure/denied.do":{
				break;
			}
			case "/ers/secure/pending.do":{
				break;
			}
			case "ers/secure/dateAsc.do":{
				break;
			}
			case "ers/secure/dateDesc.do":{
				break;
			}
			default:{
				resp.sendError(404);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		this.doPost(req, resp);
	}


}
