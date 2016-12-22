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
	
	public List<Reim> getAccepted(List<Reim> original);
	
	public List<Reim> getDenied(List<Reim> original);
	
	public List<Reim> getPending(List<Reim> original);
	
	public List<Reim> geStatusAscend(List<Reim> original);
	
	public List<Reim> getStatusDescend(List<Reim> original);
	
	public List<Reim> getTypeAscend(List<Reim> original);
	
	public List<Reim> getTypeDescend(List<Reim> orignal);
	
	public List<Reim> getDateAscend(List<Reim> original);
	
	public List<Reim> getDateDescend(List<Reim> original);
	
	public void changeStatus(Reim reim, User user, Status status)
			throws ServiceUnavailableException, UnauthorizedException;
	
	public Reim createReim(User user, double amount, Type type, Status status, String description)
			throws ServiceUnavailableException, ValidateException;

	public List<Type> getAllTypes() throws ServiceUnavailableException;

	public List<Status> getAllStatus() throws ServiceUnavailableException;
}
