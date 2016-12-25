package com.ers.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.data.DataFactory;
import com.ers.exception.UnauthorizedException;
import com.ers.exception.ValidateException;

class ReimService {
	private static ReimService INSTANCE = null;
	
	private ReimService(){}
	
	synchronized public static ReimService getInstance(){
		if(INSTANCE == null)
			INSTANCE = new ReimService();
		return INSTANCE;
	}

	void changeStatus(Reim reim, User user, Status status) 
			throws ServiceUnavailableException, UnauthorizedException{
		// ensure user is a manager
		if(!user.getRole().getRole().equals("Manager")){
			String message = "You do do not have enough permission to"
					+ " accept Reimbursements";
			throw new UnauthorizedException(message);
		}
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DataFactory.getFacade().updateReimStatus(reim.getId(), user.getId(), status.getId(), ts);
		reim.setStatus(new Status(status.getId(), status.getStatus()));
		reim.setResolved(ts);
		reim.setResolver(user);
	}

	Reim createReim(User user, double amount, Type type, Status status, String description) 
			throws ServiceUnavailableException, ValidateException{
		if(amount > 4000){
			String message = "Please enter a value that is under $4000.";
			throw new ValidateException(message);
		}
		if(description.length() > 250){
			String message = "Please enter a description that is 250 characters or less.";
			throw new ValidateException(message);
		}
		return DataFactory.getFacade().createReim(user, amount, type, status, description);
	}

	List<Reim> getReims(User user) throws ServiceUnavailableException {
		List<Reim> list;
		
		if(user.getRole().getRole().equals("Manager"))
			list = DataFactory.getFacade().getAllReims();
		else
			list = DataFactory.getFacade().getUserReims(user.getId());
		Collections.sort(list);
		return list;
	}
	
	List<Type> getAllTypes() throws ServiceUnavailableException {
		return DataFactory.getFacade().getAllTypes(); 
	}
	
	List<Status> getAllStatus() throws ServiceUnavailableException {
		return DataFactory.getFacade().getAllStatus();
	}
	
	List<Reim> getReimByStatus(User user, String status) throws ServiceUnavailableException{
		List<Reim> list;
		
		if(user.getRole().getRole().equals("Manager"))
			list = DataFactory.getFacade().getAllReimsByStatus(status);
		else
			list = DataFactory.getFacade().getUserReimsByStatus(user.getId(), status);
		
		Collections.sort(list);
		return list;
	}
	
	List<Reim> getReimByType(User user, String type) throws ServiceUnavailableException{
		List<Reim> list;
		
		if(user.getRole().getRole().equals("Manager"))
			list = DataFactory.getFacade().getAllReimsByType(type);
		else
			list = DataFactory.getFacade().getUserReimsByType(user.getId(), type);
		
		Collections.sort(list);
		return list;
	}
}