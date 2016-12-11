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

	//TODO: validate, create then pass Reim then pass it
	Reim createReim(User user, double amount, Type type, Status status, String description) 
			throws ServiceUnavailableException{
		return DataFactory.getFacade().createReim(user, amount, type, status, description);
		
		
	}

	public List<Reim> getReims(User user) throws ServiceUnavailableException {
		if(user.getRole().getRole().equals("Manager"))
			return getAllReims();
		return getUserReims(user);
	}
	
	/* Helper Functions */
	
	private List<Reim> getAllReims() 
			throws ServiceUnavailableException{
		List<Reim> list = DataFactory.getFacade().getAllReims();
		return list;
	}

	private List<Reim> getUserReims(User user) 
			throws ServiceUnavailableException{
		List<Reim> list = DataFactory.getFacade().getUserReims(user.getUsername());
		return list;
	}
	
	public boolean validateAmount(String amt){
		double amount = Double.valueOf(amt); 
		
		if(amount > 0){
			String amountString = Double.toString(amount);
			int decimalPoint = amountString.indexOf('.');
			int numDecimals = amountString.length() - decimalPoint -1;
			if(numDecimals <= 2){
				return true;
			}
				
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(Reimbursement.getInstance().validateAmount("0.234"));
	}
}