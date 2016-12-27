package com.ers.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = req.getSession();
		
		List<Reim> list = (List<Reim>)session.getAttribute("reims");
		String sortInUse = (String)session.getAttribute("sortInUse");
		if(sortInUse == null) sortInUse = "norm";
		
		if(sortInUse.equals("inverse")){
			session.setAttribute("sortInUse", "norm");
			BusinessFactory.getDelegate().sortDate(list, false);
		}else{
			session.setAttribute("sortInUse", "inverse");
			BusinessFactory.getDelegate().sortDate(list, true);
		}
		req.setAttribute("currentSort", 1);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortAmount(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		
		List<Reim> list = (List<Reim>)session.getAttribute("reims");
		String sortInUse = (String)session.getAttribute("sortInUse");
		if(sortInUse == null) sortInUse = "norm";
		
		if(sortInUse.equals("inverse")){
			session.setAttribute("sortInUse", "norm");
			BusinessFactory.getDelegate().sortAmount(list, false);
		}else{
			session.setAttribute("sortInUse", "inverse");
			BusinessFactory.getDelegate().sortAmount(list, true);
		}
		
		req.setAttribute("currentSort", 2);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortType(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		List<Reim> list = (List<Reim>)session.getAttribute("reims");
		String sortInUse = (String)session.getAttribute("sortInUse");
		if(sortInUse == null) sortInUse = "norm";
		
		if( sortInUse.equals("inverse")){
			session.setAttribute("sortInUse", "norm");
			BusinessFactory.getDelegate().sortType(list, false);
		}else{
			session.setAttribute("sortInUse", "inverse");
			BusinessFactory.getDelegate().sortType(list, true);
		}
		
		req.setAttribute("currentSort", 3);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortStatus(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		
		List<Reim> list = (List<Reim>)session.getAttribute("reims");
		String sortInUse = (String)session.getAttribute("sortInUse");
		if(sortInUse == null) sortInUse = "norm";
		
		if( sortInUse.equals("statusInverse") ){
			session.setAttribute("sortInUse", "norm");
			BusinessFactory.getDelegate().sortStatus(list, false);
		}else{
			session.setAttribute("sortInUse", "inverse");
			BusinessFactory.getDelegate().sortStatus(list, true);
		}
		
		req.setAttribute("currentSort", 4);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	public void sortDescription(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		HttpSession session = req.getSession();
		
		List<Reim> list = (List<Reim>)session.getAttribute("reims");
		String sortInUse = (String)session.getAttribute("sortInUse");
		if(sortInUse == null) sortInUse = "norm";
		
		if(sortInUse.equals("descriptionInverse")){
			session.setAttribute("sortInUse", "norm");
			BusinessFactory.getDelegate().sortDescription(list, false);
		}else{
			session.setAttribute("sortInUse", "inverse");
			BusinessFactory.getDelegate().sortDescription(list, true);
		}
		req.setAttribute("currentSort", 5);
		req.getRequestDispatcher("/WEB-INF/pages/main.jsp")
		.forward(req, resp);
	}

	
}
