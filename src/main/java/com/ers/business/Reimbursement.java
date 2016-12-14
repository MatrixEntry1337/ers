package com.ers.business;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.data.DataFactory;
import com.ers.exception.UnauthorizedException;

class Reimbursement {
	private static Reimbursement INSTANCE = null;
	
	private Reimbursement(){}
	
	synchronized public static Reimbursement getInstance(){
		if(INSTANCE == null)
			INSTANCE = new Reimbursement();
		return INSTANCE;
	}

	Reim changeStatus(Reim reim, User user, Status status) 
			throws ServiceUnavailableException, UnauthorizedException{
		// ensure user is a manager
		if(!user.getRole().getRole().equals("Manager")){
			throw new UnauthorizedException();
		}
		
		DataFactory.getFacade().updateReimStatus(reim.getId(), user.getId(), status.getId());
		reim.setStatus(new Status(status.getId(), status.getStatus()));
		return reim;
	}

	//TODO: validate, then pass Reim 
	Reim createReim(User user, double amount, Type type, Status status, String description) 
			throws ServiceUnavailableException{
		return DataFactory.getFacade().createReim(user, amount, type, status, description);
		
		
	}

	public List<Reim> getReims(User user) throws ServiceUnavailableException {
		// decide based on role if to call reims for a specific user
		// or to call for all reims
		if(user.getRole().getRole().equals("Manager"))
			return getAllReims();
		return getUserReims(user);
	}
	
	/* Helper Functions */
	
	private List<Reim> getAllReims() 
			throws ServiceUnavailableException{
		return  DataFactory.getFacade().getAllReims();
	}

	private List<Reim> getUserReims(User user) 
			throws ServiceUnavailableException{
		return DataFactory.getFacade().getUserReims(user.getId());
	}

	public List<Type> getAllTypes() throws ServiceUnavailableException {
		return DataFactory.getFacade().getAllTypes(); 
	}
	
	public List<Status> getAllStatus() throws ServiceUnavailableException {
		return DataFactory.getFacade().getAllStatus();
	}
}