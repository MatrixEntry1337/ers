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

	List<Reim> getReims(int userId) throws SQLException{
		List<Reim> list = new ArrayList<Reim>();
		String sql = "SELECT REIMB_ID, REIMB_AMOUNT,"
				+ " s.REIMB_STATUS_ID, s.REIMB_STATUS, t.REIMB_TYPE_ID, t.REIMB_TYPE,"
				+ " REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_RESOLVED,"
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
				+ " Left JOIN ERS_USERS m"
				+ " ON e.REIMB_RESOLVER = m.ERS_USERS_ID"
				+ " WHERE e.REIMB_AUTHOR = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		mapReimRows(rs, list, true);
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
		mapReimRows(rs, list, false);
		return list;
	}

	private void mapReimRows(ResultSet rs, List<Reim> list, boolean userType) throws SQLException{
		int id; 
		double amount;
		User author, resolver;
		Status status;
		Type type;
		String description;
		Timestamp submitted, resolved;
		Reim reim;
		if(userType){
			while(rs.next()){
				id = rs.getInt("REIMB_ID");
				amount = rs.getDouble("REIMB_AMOUNT");
				author = null;
				resolver = constructUser(rs, false);
				status = new Status(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
				type = new Type(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
				description = rs.getString("REIMB_DESCRIPTION");
				submitted = rs.getTimestamp("REIMB_SUBMITTED");
				resolved = rs.getTimestamp("REIMB_RESOLVED");
				reim = new Reim(id, amount, submitted, resolved, description, author, resolver, status, type);
				list.add(reim);
			}
		}else{
			while(rs.next()){

				id = rs.getInt("REIMB_ID");
				amount = rs.getDouble("REIMB_AMOUNT");
				author = constructUser(rs, true);
				resolver = constructUser(rs, false);
				status = new Status(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
				type = new Type(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
				description = rs.getString("REIMB_DESCRIPTION");
				submitted = rs.getTimestamp("REIMB_SUBMITTED");
				resolved = rs.getTimestamp("REIMB_RESOLVED");
				reim = new Reim(id, amount, submitted, resolved, description, author, resolver, status, type);
				list.add(reim);
			} 
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
			if(rs.getString("A_USERNAME") == null)
				return null;
			id = rs.getInt("A_USERS_ID");
			username = rs.getString("A_USERNAME");
			lastName = rs.getString("A_LAST_NAME");
			firstName = rs.getString("A_FIRST_NAME");
			email = rs.getString("A_EMAIL");
		}
		return new User(id, username, firstName, lastName, email, null);
	}

	Reim insertReim(User author, double amount, Type type, Status status, String description)
			throws SQLException{
		Reim reim = null; 
		String sql = "INSERT INTO"
				+ " ERS_REIMBURSEMENT"
				+ " (REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION,"
				+ " REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) "
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql, new String[]{"REIMB_ID"});
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		stmt.setDouble(1, amount);
		stmt.setTimestamp(2, ts);
		stmt.setString(3, description);
		stmt.setInt(4, author.getId());
		stmt.setInt(5, status.getId());
		stmt.setInt(6, type.getId());
		stmt.executeQuery();
		ResultSet rs = stmt.getGeneratedKeys();
		rs.next();
		int generatedPK = rs.getInt(1);
		reim = constructReim(generatedPK, author, amount, type, status, description, ts);
		return reim;
	}

	private Reim constructReim(int id, User author, double amount, Type type, Status status, String description, Timestamp submitted){
		Reim reim = new Reim(id, amount, submitted, null, description, author, null, status, type);
		return reim;
	}

	void setReimStatus(int reim, int resolver, int status, Timestamp ts) throws SQLException{
		String sql = "UPDATE ERS_REIMBURSEMENT"
				+ " SET REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?,"
				+ " REIMB_RESOLVED = ?"
				+ " WHERE REIMB_ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, status);
		stmt.setInt(2, resolver);
		stmt.setTimestamp(3, ts);
		stmt.setInt(4, reim);
		stmt.executeQuery();
	}

	List<Status> getAllStatus() throws SQLException{
		List<Status> list = new ArrayList<Status>();
		String sql = "SELECT REIMB_STATUS_ID, REIMB_STATUS"
				+ " FROM ERS_REIMBURSEMENT_STATUS";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapStatusRows(rs, list);
		return list;
	}

	private void mapStatusRows(ResultSet rs, List<Status> list) throws SQLException{
		int id;
		String statusName;
		Status status;
		while(rs.next()){
			id = rs.getInt("REIMB_STATUS_ID");
			statusName = rs.getString("REIMB_STATUS"); 
			status = new Status(id, statusName);
			list.add(status);
		}
	}

	List<Type> getAllTypes() throws SQLException{
		List<Type> list = new ArrayList<Type>();
		String sql = "SELECT REIMB_TYPE_ID, REIMB_TYPE"
				+ " FROM ERS_REIMBURSEMENT_TYPE";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		mapTypeRows(rs, list);
		return list;
	}
	
	private void mapTypeRows(ResultSet rs, List<Type> list) throws SQLException{
		int id;
		String typeName;
		Type type;
		while(rs.next()){
			id = rs.getInt("REIMB_TYPE_ID");
			typeName = rs.getString("REIMB_TYPE"); 
			type = new Type(id, typeName);
			list.add(type);
		}
	}
}


