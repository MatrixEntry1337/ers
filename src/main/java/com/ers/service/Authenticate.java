package com.ers.service;


import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import org.mindrot.jbcrypt.BCrypt;

import com.ers.beans.User;
import com.ers.data.DataFacadeInterface;
import com.ers.data.DataFactory;

class Authenticate {
	
	private static Authenticate INSTANCE = null;
	
	private Authenticate(){}
	
	synchronized public static Authenticate getInstance(){
		if(INSTANCE == null)
			INSTANCE = new Authenticate();
		return INSTANCE;
	}
	
	User authenticate(String username, String password) 
			throws AuthenticationException, ServiceUnavailableException{
		
		DataFacadeInterface facade = DataFactory.getFacade();
		User user = null;
		String hash = facade.getHash(username);
		if(hash == null || !BCrypt.checkpw(password, hash)){ 
			System.out.println("User login failed");
			throw new AuthenticationException();
		}else{
			user = facade.getUser(username);
			System.out.println("User login successful");
		}
		return user;
	}
}

