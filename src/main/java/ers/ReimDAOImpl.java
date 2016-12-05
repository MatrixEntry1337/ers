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
	public List<Reim> getReims() {

		List<Reim> list = new ArrayList<Reim>();
		try{
			String sql = "SELECT * FROM ERS_REIMBURSEMENT";
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
			reim.setStatusId(statusId);
			typeId = rs.getInt("REIMB_TYPE_ID");
			reim.setTypeId(typeId);
			description = rs.getString("REIMB_DESCRIPTION");
			reim.setDescription(description);
			submitted = rs.getTimestamp("REIMB_SUBMITTED");
			reim.setSubmitted(submitted);
			resolved = rs.getTimestamp("REIMB_RESOLVED");
			reim.setResolved(resolved);
			list.add(reim);
		} 
	}

	public Reim createReim(int id, int amount, int author, int typeId, String description){
		Reim reim = reimCreator(id, amount, author, typeId, description);
		String sql = "INSERT INTO "
				+"ERS_REIMBURSEMENT(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID) "
				+"VALUES(?, ?, ?, ?, ?, ?, ?)";
		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.setInt(2, amount);
			stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			stmt.setString(4, description);
			stmt.setInt(5, author);
			stmt.setInt(6, 1);
			stmt.setInt(7, 1);
			stmt.executeQuery();
		}catch(SQLException e){
			System.out.println("SQL Exception at create a Reimburement");
			e.printStackTrace();
		}
		return reim;
	}

	private Reim reimCreator(int id, int amount, int author, int typeId, String description){
		Reim reim = new Reim();
		reim.setId(id);
		reim.setAmount(amount);
		reim.setAuthor(author);
		reim.setTypeId(typeId);
		reim.setDescription(description);
		reim.setSubmitted(new Timestamp(System.currentTimeMillis()));
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


