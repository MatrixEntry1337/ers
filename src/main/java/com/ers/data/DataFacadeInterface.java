package com.ers.data;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;

public interface DataFacadeInterface {
	
	public List<Reim> getAllReims() throws ServiceUnavailableException;
	
	public List<Reim> getUserReims(int userId) throws ServiceUnavailableException;
	
	public Reim createReim(User author, double amount, Type type, Status status, String description) throws ServiceUnavailableException;
	
	public String getHash(String username) throws ServiceUnavailableException;
	
	public User getUser(String username) throws ServiceUnavailableException;
	
	public void updateReimStatus(int reim, int resolve, int status) throws ServiceUnavailableException;
}