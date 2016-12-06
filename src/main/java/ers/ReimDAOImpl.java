package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReimDAOImpl implements ReimDAO {

	private Connection conn;

	public ReimDAOImpl(Connection conn){
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
		int id, amount, author, resolver, statusId, typeId;
		String description;
		Timestamp submitted, resolved;

		while(rs.next()){
			Reim reim = new Reim();
			id = rs.getInt("REIMB_ID");
			reim.setId(id);
			amount = rs.getInt("REIMB_AMOUNT");
			reim.setAmount(amount);
			author = rs.getInt("REIMB_AUTHOR");
			reim.setAuthor(author);
			resolver = rs.getInt("REIMB_RESOLVER");
			reim.setResolver(resolver);
			statusId = rs.getInt("REIMB_STATUS_ID");
			reim.setStatus(statusId, rs.getString("REIMB_STATUS"));
			typeId = rs.getInt("REIMB_TYPE_ID");
			reim.setType(typeId, rs.getString("REIMB_TYPE"));
			description = rs.getString("REIMB_DESCRIPTION");
			reim.setDescription(description);
			submitted = rs.getTimestamp("REIMB_SUBMITTED");
			reim.setSubmitted(submitted);
			resolved = rs.getTimestamp("REIMB_RESOLVED");
			reim.setResolved(resolved);
			list.add(reim);
		} 
	}

	public Reim createReim(int amount, int author, int typeId, String description){
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
			stmt.setInt(4, author);
			stmt.setInt(5, 1);
			stmt.setInt(6, typeId);
			stmt.executeQuery();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			int generatedPK = rs.getInt(1);
			reim = reimCreator(generatedPK, amount, author, typeId, description, ts);
		}catch(SQLException e){
			System.out.println("SQL Exception at create a Reimburement");
			e.printStackTrace();
		}
		return reim;
	}

	private Reim reimCreator(int id, int amount, int author, int typeId, String description, Timestamp submitted){
		Reim reim = new Reim();
		reim.setId(id);
		reim.setAmount(amount);
		reim.setAuthor(author);
		reim.setType(typeId, null);
		reim.setDescription(description);
		reim.setSubmitted(submitted); 
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


