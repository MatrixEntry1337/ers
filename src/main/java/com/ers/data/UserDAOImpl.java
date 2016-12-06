package com.ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ers.beans.Role;
import com.ers.beans.User;



class UserDAOImpl implements UserDAO {
	private Connection conn;

	UserDAOImpl(Connection conn){
		super();
		this.conn = conn;
	}

	public User getUser(String username){
		User user = null;
		String sql = "SELECT *" 
					+ " FROM ERS_USERS"
					+ " JOIN ERS_USER_ROLES"
					+ " ON ERS_USERS.USER_ROLE_ID = ERS_USER_ROLES.ERS_USER_ROLE_ID"
					+ " WHERE ERS_USERNAME = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			user = constructUser(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	private User constructUser(ResultSet rs) throws SQLException{
		if(rs.next()){
			int id = rs.getInt("ERS_USERS_ID");
			String username = rs.getString("ERS_USERNAME");
			String lastName = rs.getString("USER_LAST_NAME");
			String firstName = rs.getString("USER_FIRST_NAME");
			String email = rs.getString("USER_EMAIL");
			Role role = new Role(rs.getInt("USER_ROLE_ID"), rs.getString("USER_ROLE"));
			return new User(id, username, firstName, lastName, email, role);
		}
		return null;
	}
	
	public String getPassword(String username){
		String password = null; 
		String sql = "SELECT ERS_USERNAME, ERS_PASSWORD FROM ERS_USERS WHERE ERS_USERNAME = ?";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			// set username in query
			stmt.setString(1, username);
			//grab results
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) password = rs.getString("ERS_PASSWORD");
		}catch(SQLException e){
			System.out.println("SQL broken at getCreds method");
			e.printStackTrace();
		}
		return password;
	}
	
}
