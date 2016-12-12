package com.ers.business;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;


public class Test {
	
	public static void main(String[] args) throws SQLException, AuthenticationException, ServiceUnavailableException {
		
		// Test search for user
		User user = BusinessFactory.getDelegate().authenticateUser("thanks", "password1");
		System.out.println(user);
		
		//List 
		List<Reim> list = BusinessFactory.getDelegate().getReims(user);
		System.out.println(list);
		
		//Test creation
		Type type = new Type(1, "Type A");
		Status status = new Status(1, "Pending");
		System.out.println(BusinessFactory.getDelegate().createReim(user, 56, type, status, "Care Rental"));
		
		
		System.out.println("Complete!");
	}
}