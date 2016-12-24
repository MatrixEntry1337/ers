package com.ers.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;


public class DataFacade implements DataFacadeInterface{

	// message that is sent to to the requester when there is a SQLException
	private static final String suMessage = "Reimbursement cannot be added at this time. "
			+ "Please try again later.";

	private static DataFacade INSTANCE = null;

	private DataFacade(){}

	synchronized public static DataFacade getInstance(){
		if (INSTANCE == null)
			INSTANCE = new DataFacade();
		return INSTANCE;
	}

	private Connection getConnection() 
			throws SQLException{
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	@Override
	public List<Reim> getAllReims() 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getAllReims();
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Reim> getAllReimsByStatus(String status) throws ServiceUnavailableException {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getAllReimsByStatus(status);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Reim> getAllReimsByType(String type) throws ServiceUnavailableException {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getAllReimsByType(type);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<Reim> getUserReims(int userId) 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getUserReims(userId);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException(suMessage);			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Reim> getUserReimsByStatus(int userId, String status) throws ServiceUnavailableException {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getUserReimsByStatus(userId, status);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public List<Reim> getUserReimsByType(int userId, String type) throws ServiceUnavailableException {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getUserReimsByType(userId, type);
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public Reim createReim(User author, double amount, Type type, Status status, String description) 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			Reim reim = dao.insertReim(author, amount, type, status, description);
			conn.commit();
			return reim;
		}catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public String getHash(String username) 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			String hashedPassword = dao.getPassword(username);
			return hashedPassword;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public User getUser(String username) 
			throws ServiceUnavailableException{
		User user = null;
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			user = dao.getUser(username);
			return user;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateReimStatus(int reim, int resolver, int status, Timestamp ts) 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			dao.setReimStatus(reim, resolver, status, ts);
			conn.commit();
		}catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Type> getAllTypes() throws ServiceUnavailableException{ 
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Type> list = dao.getAllTypes();
			return list;
		}catch(SQLException e){
			e.printStackTrace();;
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Status> getAllStatus() throws ServiceUnavailableException {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Status> list = dao.getAllStatus();
			return list;
		}catch(SQLException e){
			e.printStackTrace();;
			throw new ServiceUnavailableException(suMessage);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
