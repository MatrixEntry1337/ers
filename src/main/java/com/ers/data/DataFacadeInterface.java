package com.ers.data;

import java.sql.Timestamp;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;

public interface DataFacadeInterface {
	
	public List<Reim> getAllReims() throws ServiceUnavailableException;
	
	public List<Reim> getAllReimsByStatus(String status) throws ServiceUnavailableException;
	
	public List<Reim> getAllReimsByType(String type) throws ServiceUnavailableException;
	
	public List<Reim> getUserReims(int userId) throws ServiceUnavailableException;
	
	public List<Reim> getUserReimsByStatus(int userId, String status) throws ServiceUnavailableException;
	
	public List<Reim> getUserReimsByType(int userId, String status) throws ServiceUnavailableException;
	
	public Reim createReim(User author, double amount, Type type, Status status, String description) throws ServiceUnavailableException;
	
	public String getHash(String username) throws ServiceUnavailableException;
	
	public User getUser(String username) throws ServiceUnavailableException;
	
	public void updateReimStatus(int reim, int resolve, int status, Timestamp ts) throws ServiceUnavailableException;

	public List<Type> getAllTypes() throws ServiceUnavailableException;
	
	public List<Status> getAllStatus() throws ServiceUnavailableException;
	
}