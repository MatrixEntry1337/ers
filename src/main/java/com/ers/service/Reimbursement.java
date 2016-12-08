package com.ers.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.User;
import com.ers.data.DataFacade;

class Reimbursement {

	List<Reim> all(){
		List<Reim> list = new DataFacade().getAllReims();
		return list;
	}

	List<Reim> myReims(User user) throws ServiceUnavailableException{
		List<Reim> list = new DataFacade().getReims(user.getUsername());
		return list;
	}
	
	Reim acceptReim(Reim reim, User Manager) throws ServiceUnavailableException{
		if(new DataFacade().updateReimStatus(reim.getId(), Manager.getId(), 2))
			reim.setStatus(new Status(2, "Accepted"));	
		return reim;
	}

	Reim denyReim(Reim reim, User Manager) throws ServiceUnavailableException{
		if(new DataFacade().updateReimStatus(reim.getId(), Manager.getId(), 3))
			reim.setStatus(new Status(3, "Denied"));
		return reim;
	}

	Reim createReim(User user, int amount, int type, String description) throws ServiceUnavailableException{
		return new DataFacade().createReim(user, amount, type, description);
	}
	
}
