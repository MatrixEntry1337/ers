package com.ers.service;


import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import org.mindrot.jbcrypt.BCrypt;

import com.ers.beans.User;
import com.ers.data.DataFacade;

class Authenticate {

	User authenticate(String username, String password) throws AuthenticationException, ServiceUnavailableException{
		DataFacade facade = new DataFacade();
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
