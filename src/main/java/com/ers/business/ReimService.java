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
		//TODO: validate, then pass Reim
		if(amount > 4000){
			String message = "Please enter a value that is under $4000";
			throw new ValidateException(message);
		}
		return DataFactory.getFacade().createReim(user, amount, type, status, description);
	}

	List<Reim> getReims(User user) throws ServiceUnavailableException {
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
		List<Reim> list = DataFactory.getFacade().getAllReims();
		Collections.sort(list);
		return list;
	}

	List<Reim> getUserReims(User user) 
			throws ServiceUnavailableException{
		List<Reim> list = DataFactory.getFacade().getUserReims(user.getId());
		Collections.sort(list);
		return list;
	}

	List<Type> getAllTypes() throws ServiceUnavailableException {
		return DataFactory.getFacade().getAllTypes(); 
	}
	
	List<Status> getAllStatus() throws ServiceUnavailableException {
		return DataFactory.getFacade().getAllStatus();
	}
	
	// TODO sort algos here
	List<Reim> getAcceptedOnly(User user) throws ServiceUnavailableException{
		List<Reim> list;
		if(user.getRole().getRole().equals("Manager")){
			list = DataFactory.getFacade().getAllAcceptedReims();
		}else{
			list = DataFactory.getFacade().getUserAcceptedReims(user.getId());
		}
		Collections.sort(list);
		return list;
	}
	
	List<Reim> getDeniedOnly(User user) throws ServiceUnavailableException{
		List<Reim> list;
		if(user.getRole().getRole().equals("Manager")){
			list = DataFactory.getFacade().getAllDeniedReims();
		}else{
			list = DataFactory.getFacade().getUserDeniedReims(user.getId());
		}
		Collections.sort(list);
		return list;
	}
	
	List<Reim> getPendingOnly(User user) throws ServiceUnavailableException{
		List<Reim> list;
		if(user.getRole().getRole().equals("Manager")){
			list = DataFactory.getFacade().getAllPendingReims();
		}else{
			list = DataFactory.getFacade().getUserPendingReims(user.getId());
		}
		Collections.sort(list);
		return list;
	}
	
}