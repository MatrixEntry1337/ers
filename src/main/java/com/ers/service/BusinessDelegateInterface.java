package com.ers.service;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.exception.UnauthorizedException;

public interface BusinessDelegateInterface {
	public User authenticateUser(String username, String password)
			throws AuthenticationException, ServiceUnavailableException;
	
	public List<Reim> getReims(User user) throws ServiceUnavailableException;
	
	public Reim changeStatus(Reim reim, User user, Status status)
			throws ServiceUnavailableException, UnauthorizedException;
	
	public Reim createReim(User user, int amount, Type type, Status status, String description)
			throws ServiceUnavailableException;
}
