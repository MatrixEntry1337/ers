package com.ers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
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
			case "/ers/secure/Accepted.do":{
				DataGrabController.getInstance().getAccepted(req, resp);
				break;
			}
			case "/ers/secure/Denied.do":{
				DataGrabController.getInstance().getDenied(req, resp);
				break;
			}
			case "/ers/secure/Pending.do":{
				DataGrabController.getInstance().getPending(req, resp);
				break;
			}
			case "/ers/secure/Food.do":{
				DataGrabController.getInstance().getFood(req, resp);
				break;
			}
			case "/ers/secure/Lodging.do":{
				DataGrabController.getInstance().getLodging(req, resp);
				break;
			}
			case "/ers/secure/Travel.do":{
				DataGrabController.getInstance().getTravel(req, resp);
				break;
			}
			case "/ers/secure/Other.do":{
				DataGrabController.getInstance().getOther(req, resp);
				break;
			}
			case "/ers/secure/price.do":{
				DataSortController.getInstance().sortAmount(req, resp);
				break;
			}
			case "/ers/secure/date.do":{
				DataSortController.getInstance().sortDate(req, resp);
				break;
			}
			case "/ers/secure/type.do":{
				DataSortController.getInstance().sortType(req, resp);
				break;
			}
			case "/ers/secure/status.do":{
				DataSortController.getInstance().sortStatus(req, resp);
				break;
			}
			case "/ers/secure/description.do":{
				DataSortController.getInstance().sortDescription(req, resp);
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
