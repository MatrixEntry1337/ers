package com.ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ers.beans.Reim;
import com.ers.beans.Status;
import com.ers.beans.Type;
import com.ers.beans.User;

class ReimDAO{
	private Connection conn;

	ReimDAO(Connection conn){
		super();
		this.conn = conn;
	}

	List<Reim> getReims(String username) throws SQLException{
		List<Reim> list = new ArrayList<Reim>();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_RESOLVER,"
				+ " s.REIMB_STATUS_ID, s.REIMB_STATUS, t.REIMB_TYPE_ID, t.REIMB_TYPE,"
				+ " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
				+ "	a.ERS_USERS_ID, a.ERS_USERNAME, a.USER_FIRST_NAME, a.USER_LAST_NAME, a.USER_EMAIL,"
				+ " m.ERS_USERS_ID AS A_USERS_ID,"
				+ " m.ERS_USERNAME AS A_USERNAME,"
				+ " m.USER_FIRST_NAME AS A_FIRST_NAME,"
				+ " m.USER_LAST_NAME AS A_LAST_NAME,"
				+ " m.USER_EMAIL AS A_EMAIL"
				+ " FROM ERS_REIMBURSEMENT e"
				+ " JOIN ERS_REIMBURSEMENT_TYPE t"
				+ " ON e.REIMB_TYPE_ID = t.REIMB_TYPE_ID"
				+ " JOIN ERS_REIMBURSEMENT_STATUS s"
				+ " ON e.REIMB_STATUS_ID = s.REIMB_STATUS_ID"
				+ " INNER JOIN ERS_USERS a"
				+ " ON e.REIMB_AUTHOR = a.ERS_USERS_ID"
				+ " Left JOIN ERS_USERS m"
				+ " ON e.REIMB_RESOLVER = m.ERS_USERS_ID"
				+ " WHERE a.ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, list);
		return list;
	}

	List<Reim> getAllReims()throws SQLException {

		List<Reim> list = new ArrayList<Reim>();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT, REIMB_RESOLVER,"
				+ " s.REIMB_STATUS_ID, s.REIMB_STATUS, t.REIMB_TYPE_ID, t.REIMB_TYPE,"
				+ " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
				+ "	a.ERS_USERS_ID, a.ERS_USERNAME, a.USER_FIRST_NAME, a.USER_LAST_NAME, a.USER_EMAIL,"
				+ " m.ERS_USERS_ID AS A_USERS_ID,"
				+ " m.ERS_USERNAME AS A_USERNAME,"
				+ " m.USER_FIRST_NAME AS A_FIRST_NAME,"
				+ " m.USER_LAST_NAME AS A_LAST_NAME,"
				+ " m.USER_EMAIL AS A_EMAIL"
				+ " FROM ERS_REIMBURSEMENT e"
				+ " JOIN ERS_REIMBURSEMENT_TYPE t"
				+ " ON e.REIMB_TYPE_ID = t.REIMB_TYPE_ID"
				+ " JOIN ERS_REIMBURSEMENT_STATUS s"
				+ " ON e.REIMB_STATUS_ID = s.REIMB_STATUS_ID"
				+ " INNER JOIN ERS_USERS a"
				+ " ON e.REIMB_AUTHOR = a.ERS_USERS_ID"
				+ " Left JOIN ERS_USERS m"
				+ " ON e.REIMB_RESOLVER = m.ERS_USERS_ID";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		mapRows(rs, list);
		System.out.println("SQLException at getAllReims");
		return list;
	}

	private void mapRows(ResultSet rs, List<Reim> list) throws SQLException{
		int id, amount;
		User author, resolver;
		Status status;
		Type type;
		String description;
		Timestamp submitted, resolved;

		while(rs.next()){

			id = rs.getInt("REIMB_ID");
			amount = rs.getInt("REIMB_AMOUNT");
			author = constructUser(rs, true);
			resolver = constructUser(rs, false);
			status = new Status(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
			type = new Type(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
			description = rs.getString("REIMB_DESCRIPTION");
			submitted = rs.getTimestamp("REIMB_SUBMITTED");
			resolved = rs.getTimestamp("REIMB_RESOLVED");
			Reim reim = new Reim(id, amount, submitted, resolved, description, author, resolver, status, type);
			list.add(reim);
		} 
	}

	private User constructUser(ResultSet rs, boolean userType) throws SQLException{
		int id;
		String username, lastName, firstName, email;
		if(userType){
			id = rs.getInt("ERS_USERS_ID");
			username = rs.getString("ERS_USERNAME");
			lastName = rs.getString("USER_LAST_NAME");
			firstName = rs.getString("USER_FIRST_NAME");
			email = rs.getString("USER_EMAIL");
		}else{
			id = rs.getInt("A_USERS_ID");
			username = rs.getString("A_USERNAME");
			lastName = rs.getString("A_LAST_NAME");
			firstName = rs.getString("A_FIRST_NAME");
			email = rs.getString("A_EMAIL");
		}
		return new User(id, username, firstName, lastName, email, null);
	}

	Reim insertReim(User author, int amount, int type, String description){
		Reim reim = null; 
		String sql = "INSERT INTO"
				+ " ERS_REIMBURSEMENT"
				+ " (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION,"
				+ " REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"REIMB_ID"});
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			stmt.setInt(1, amount);
			stmt.setTimestamp(2, ts);
			stmt.setString(3, description);
			stmt.setInt(4, author.getId());
			stmt.setInt(5, 1);
			stmt.setInt(6, type);
			stmt.executeQuery();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int generatedPK = rs.getInt(1);
			reim = constructReim(generatedPK, author, amount, type, description, ts);
		}catch(SQLException e){
			System.out.println("SQL Exception at create a Reimburement");
			e.printStackTrace();
		}
		return reim;
	}

	private Reim constructReim(int id, User author, int amount, int typeId, String description, Timestamp submitted){
		Type type = new Type(typeId, null);
		Status status = new Status(1, "Pending");
		Reim reim = new Reim(id, amount, submitted, null, description, author, null, status, type);
		return reim;
	}

	void setReimStatus(int reim, int resolver, int status) throws SQLException{
		String sql = "UPDATE ERS_REIMBURSEMENT"
				+ " SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?"
				+ " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"REIMB_STATUS_ID"});
		stmt.setInt(1, status);
		stmt.setInt(2, resolver);
		stmt.setInt(3, reim);
		stmt.executeQuery();
		ResultSet rs = stmt.getGeneratedKeys();
	}

	boolean deleteReim(int id) {
		return false;
	}

	boolean updateReim(int id) {
		return false;
	}
}


