package com.ers.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.ServiceUnavailableException;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;


public class DataFacade implements DataFacadeInterface{

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
			conn.close();
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
			List<Reim> list = dao.getReims(userId);
			conn.close();
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
			conn.close();
			return reim;
		}catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new ServiceUnavailableException();
			}
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
	public String getHash(String username) 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			String hashedPassword = dao.getPassword(username);
			conn.close();
			return hashedPassword;
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
	public User getUser(String username) 
			throws ServiceUnavailableException{
		User user = null;
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			user = dao.getUser(username);
			conn.close();
			return user;
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
	public void updateReimStatus(int reim, int resolver, int status) 
			throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			dao.setReimStatus(reim, resolver, status);
			conn.commit();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException();
		}finally{
			try {
				conn.rollback();
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
			conn.close();
			return list;
		}catch(SQLException e){
			e.printStackTrace();;
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
	public List<Status> getAllStatus() throws ServiceUnavailableException {
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Status> list = dao.getAllStatus();
			conn.close();
			return list;
		}catch(SQLException e){
			e.printStackTrace();;
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

	
}
