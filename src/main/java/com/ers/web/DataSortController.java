package com.ers.web;

import java.io.IOException;
import java.security.Provider.Service;
import java.util.List;

import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.beans.Reim;
import com.ers.business.BusinessFactory;

public class DataSortController {
	private static DataSortController INSTANCE;
	
	private DataSortController(){
		super();
	}
	
	synchronized public static DataSortController getInstance(){
		if(INSTANCE == null)
			INSTANCE = new DataSortController();
		return INSTANCE;
	}
	public void sortDate(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		List<Reim> list = (List<Reim>)req.getSession().getAttribute("reims");
		BusinessFactory.getDelegate().sortDate(list);
		req.setAttribute("currentSort", 1);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortAmount(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		List<Reim> list = (List<Reim>)req.getSession().getAttribute("reims");
		BusinessFactory.getDelegate().sortAmount(list);
		req.setAttribute("currentSort", 2);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortType(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<Reim> list = (List<Reim>)req.getSession().getAttribute("reims");
		BusinessFactory.getDelegate().sortType(list);
		req.setAttribute("currentSort", 3);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		List<Reim> list = (List<Reim>)req.getSession().getAttribute("reims");
		BusinessFactory.getDelegate().sortStatus(list);
		req.setAttribute("currentSort", 4);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortDescription(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		List<Reim> list = (List<Reim>)req.getSession().getAttribute("reims");
		BusinessFactory.getDelegate().sortDescription(list);
		req.setAttribute("currentSort", 5);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	
}
