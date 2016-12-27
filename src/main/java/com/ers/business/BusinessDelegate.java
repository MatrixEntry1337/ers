package com.ers.business;

import java.util.Collections;
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
	public List<Reim> getReimByStatus(User user, String status) 
			throws ServiceUnavailableException {
		return ReimService.getInstance().getReimByStatus(user, status);
	}

	@Override
	public List<Reim> getReimByType(User user, String type) 
			throws ServiceUnavailableException {
		return ReimService.getInstance().getReimByType(user, type);
	}

	@Override
	public void sortStatus(List<Reim> list, boolean check) {
		ReimService.getInstance().sortStatus(list, check);
	}

	@Override
	public void sortType(List<Reim> list, boolean check) {
		ReimService.getInstance().sortType(list, check);
	}

	@Override
	public void sortDate(List<Reim> list, boolean check) {
		ReimService.getInstance().sortDate(list, check);
	}

	@Override
	public void sortAmount(List<Reim> list, boolean check) {
		ReimService.getInstance().sortAmount(list, check);
	}

	@Override
	public void sortDescription(List<Reim> list, boolean check) {
		ReimService.getInstance().sortDescripion(list, check);
	}
	
}
