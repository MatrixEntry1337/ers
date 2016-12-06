package com.ers.service;

import java.sql.SQLException;

import com.ers.beans.User;
import com.ers.data.Facade;


public class Test {
	
	public static void main(String[] args) throws SQLException {
		Facade facade = new Facade();
		User user = facade.login("gforeman", "password1");
		System.out.println(facade.getAllReims());
//		System.out.println(facade.createReim(user, 40, 2, "Food"));
		System.out.println("Complete!");
	}
}