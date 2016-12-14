package com.ers.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// logic to ensure a user has logged in
		HttpServletRequest request = (HttpServletRequest) req;
		if(request.getSession().getAttribute("user") != null){
			// they've logged in.. let them thru
			System.out.println("Authorized!");
			chain.doFilter(req, resp);
		}else{
			// they need to login first
			System.out.println("Not authorized");
			HttpServletResponse response = (HttpServletResponse) resp;
			response.setStatus(403); //forbidden
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
