package com.ers.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.User;


public class Test {
	
	public static void main(String[] args) throws SQLException, AuthenticationException, ServiceUnavailableException {
		User user = BusinessFactory.getDelegate().authenticateUser("thanks", "password1");
		System.out.println(user);
		List<Reim> list = BusinessFactory.getDelegate().all();
		System.out.println(list);
		List<Reim> list2 = BusinessFactory.getDelegate().getUserReims(user);
		System.out.println(list2);
//		System.out.println(facade.createReim(user, 40, 2, "Food"));
		System.out.println("Complete!");
	}
}