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

public class BusinessDelegate implements BusinessDelegateInterface{

	private static BusinessDelegate INSTANCE = null;
	
	private BusinessDelegate(){}
	
	synchronized public static BusinessDelegate getInstance(){
		if(INSTANCE == null)
			INSTANCE = new BusinessDelegate();
		return INSTANCE;
	}
	
	@Override
	public User authenticateUser(String username, String password) 
			throws AuthenticationException, ServiceUnavailableException {
		return Authenticate.getInstance().authenticate(username, password);
	}

	@Override
	public List<Reim> getReims(User user) throws ServiceUnavailableException{
		return ReimService.getInstance().getReims(user);
	}
	
	@Override
	public void changeStatus(Reim reim, User user, Status status) 
			throws ServiceUnavailableException, UnauthorizedException {
		ReimService.getInstance().changeStatus(reim, user, status);
	}

	@Override
	public Reim createReim(User user, double amount, Type type, Status status, String description) 
			throws ServiceUnavailableException, ValidateException {
		return ReimService.getInstance().createReim(user, amount, type, status, description);
	}

	@Override
	public List<Type> getAllTypes() throws ServiceUnavailableException {
		return ReimService.getInstance().getAllTypes();
	}

	@Override
	public List<Status> getAllStatus() throws ServiceUnavailableException {
		return ReimService.getInstance().getAllStatus();
	}

	@Override
	public List<Reim> getAccepted(User user) throws ServiceUnavailableException {
		return ReimService.getInstance().acceptedOnly(user);
	}

	@Override
	public List<Reim> getDenied(User user) throws ServiceUnavailableException {
		return ReimService.getInstance().deniedOnly(user);
	}

	@Override
	public List<Reim> getPending(User user) throws ServiceUnavailableException {
		return ReimService.getInstance().pendingOnly(user);
	}

	@Override
	public List<Reim> geStatusAscend(List<Reim> original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reim> getStatusDescend(List<Reim> original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reim> getTypeAscend(List<Reim> original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reim> getTypeDescend(List<Reim> orignal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reim> getDateAscend(List<Reim> original) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reim> getDateDescend(List<Reim> original) {
		// TODO Auto-generated method stub
		return null;
	}

}
