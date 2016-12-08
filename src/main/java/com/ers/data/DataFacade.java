package com.ers.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

import org.mindrot.jbcrypt.BCrypt;

import com.ers.beans.Reim;
import com.ers.beans.User;


public class DataFacade {

	private Connection getConnection() throws SQLException{
		Connection conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

	public List<Reim> getAllReims(){
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getAllReims();
			conn.close();
			return list;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Reim> getReims(String username) throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			List<Reim> list = dao.getReims(username);
			conn.close();
			return list;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException("Database unavailable. Please contact your administrator.");			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Reim createReim(User author, int amount, int type, String description) throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			Reim reim = dao.insertReim(author, amount, type, description);
			conn.commit();
			conn.close();
			return reim;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException("Database unavailable. Please contact your administrator.");
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

	public String getHash(String username) throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			UserDAO dao = new UserDAO(conn);
			String hashedPassword = dao.getPassword(username);
			conn.close();
			return hashedPassword;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException("Database unavailable. Please contact your administrator.");
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public User getUser(String username){
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
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	public boolean updateReimStatus(int reim, int resolver, int status) throws ServiceUnavailableException{
		Connection conn = null;
		try{
			conn = getConnection();
			ReimDAO dao = new ReimDAO(conn);
			dao.setReimStatus(reim, resolver, status);
			conn.commit();
			conn.close();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			throw new ServiceUnavailableException("Database unavailable. Please contact your administrator.");
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
}
