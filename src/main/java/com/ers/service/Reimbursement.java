package com.ers.service;

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
	
	
	List<Reim> all() 
			throws ServiceUnavailableException{
		List<Reim> list = DataFactory.getFacade().getAllReims();
		return list;
	}

	List<Reim> getUserReims(User user) 
			throws ServiceUnavailableException{
		List<Reim> list = DataFactory.getFacade().getUserReims(user.getUsername());
		return list;
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

	Reim createReim(User user, int amount, Type type, Status status, String description) 
			throws ServiceUnavailableException{
		return DataFactory.getFacade().createReim(user, amount, type, status, description);
	}	
}