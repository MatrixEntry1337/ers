package com.ers.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ers.beans.Reim;
import com.ers.beans.Role;
import com.ers.beans.Type;
import com.ers.beans.User;
import com.ers.beans.Status;



class ReimDAOImpl implements ReimDAO {

	private Connection conn;

	ReimDAOImpl(Connection conn){
		super();
		this.conn = conn;
	}

	@Override
	public List<Reim> getAllReims() {

		List<Reim> list = new ArrayList<Reim>();
		try{
			String sql = "SELECT *"
					+ " FROM ERS_REIMBURSEMENT"
					+ " JOIN ERS_REIMBURSEMENT_TYPE"
					+ " ON ERS_REIMBURSEMENT.REIMB_TYPE_ID = ERS_REIMBURSEMENT_TYPE.REIMB_TYPE_ID"
					+ " JOIN ERS_REIMBURSEMENT_STATUS"
					+ " ON ERS_REIMBURSEMENT.REIMB_STATUS_ID = ERS_REIMBURSEMENT_STATUS.REIMB_STATUS_ID"
					+ " INNER JOIN ERS_USERS"
					+ " ON ERS_REIMBURSEMENT.REIMB_AUTHOR = ERS_USERS.ERS_USERS_ID";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			mapRows(rs, list);
		}catch(SQLException e){
			System.out.println("SQLException at getAllReims");
			e.printStackTrace();
		}
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
			author = new User(rs.getInt("REIMB_AUTHOR"));
			resolver = new User(rs.getInt("REIMB_RESOLVER"));
			status = new Status(rs.getInt("REIMB_STATUS_ID"), rs.getString("REIMB_STATUS"));
			type = new Type(rs.getInt("REIMB_TYPE_ID"), rs.getString("REIMB_TYPE"));
			description = rs.getString("REIMB_DESCRIPTION");
			submitted = rs.getTimestamp("REIMB_SUBMITTED");
			resolved = rs.getTimestamp("REIMB_RESOLVED");
			Reim reim = new Reim(id, amount, submitted, resolved, description, author, resolver, status, type);
			list.add(reim);
		} 
	}

	private User constructUser(ResultSet rs) throws SQLException{
		int id = rs.getInt("ERS_USERS_ID");
		String username = rs.getString("ERS_USERNAME");
		String lastName = rs.getString("USER_LAST_NAME");
		String firstName = rs.getString("USER_FIRST_NAME");
		String email = rs.getString("USER_EMAIL");
		Role role = new Role(rs.getInt("USER_ROLE_ID"), rs.getString("USER_ROLE"));
		return new User(id, username, firstName, lastName, email, role);
	}

	public Reim createReim(User author, int amount, int typeId, String description){
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
			stmt.setInt(6, typeId);
			stmt.executeQuery();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int generatedPK = rs.getInt(1);
			reim = reimCreator(generatedPK, author, amount, typeId, description, ts);
		}catch(SQLException e){
			System.out.println("SQL Exception at create a Reimburement");
			e.printStackTrace();
		}
		return reim;
	}

	private Reim reimCreator(int id, User author, int amount, int typeId, String description, Timestamp submitted){
		Type type = new Type(typeId, null);
		Status status = new Status(1, "Pending");
		Reim reim = new Reim(id, amount, submitted, null, description, author, null, status, type);
		return reim;
	}

	@Override
	public boolean deleteReim(int id) {
		return false;
	}

	@Override
	public boolean updateReim(int id) {
		return false;
	}
}


