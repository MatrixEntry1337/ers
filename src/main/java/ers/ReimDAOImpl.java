package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		String sql = "SELECT * FROM ERS_REIMBURSEMENT";
		List<Reim> list = new ArrayList<Reim>();
		try{
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
		
		while(rs.next()){
			id = rs.getInt("REIMB_ID");
			amount = rs.getInt("REIMB_AMOUNT");
			author = rs.getInt("REIMB_AUTHOR");
			resolver = rs.getInt("REIMB_RESOLVER");
			statusId = rs.getInt("REIMB_STATUS_ID");
			typeId = rs.getInt("REIMB_TYPE_ID");
			description = rs.getString("REIMB_DESCRIPTION");
		} 
	}
	
	@Override
	public boolean deleteReim(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReim(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}


