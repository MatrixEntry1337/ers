package com.ers.business;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.data.DataFactory;
import com.ers.exception.UnauthorizedException;

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
	//TODO put in pagination here
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