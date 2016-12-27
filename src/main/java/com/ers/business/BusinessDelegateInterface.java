package com.ers.business;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.exception.UnauthorizedException;
import com.ers.exception.ValidateException;

public interface BusinessDelegateInterface {
	public User authenticateUser(String username, String password)
			throws AuthenticationException, ServiceUnavailableException;
	
	public List<Reim> getReims(User user) throws ServiceUnavailableException;
	
	public List<Reim> getReimByStatus(User user, String status) 
			throws ServiceUnavailableException;
	
	public List<Reim> getReimByType(User user, String type) 
			throws ServiceUnavailableException; 
	
	public void sortStatus(List<Reim> list);
	
	public void sortType(List<Reim> list);
	
	public void sortDate(List<Reim> list);
	
	public void sortAmount(List<Reim> list);
	
	public void sortDescription(List<Reim> list);
	
	public void changeStatus(Reim reim, User user, Status status)
			throws ServiceUnavailableException, UnauthorizedException;
	
	public Reim createReim(User user, double amount, Type type, Status status, String description)
			throws ServiceUnavailableException, ValidateException;

	public List<Type> getAllTypes() throws ServiceUnavailableException;

	public List<Status> getAllStatus() throws ServiceUnavailableException;

}
