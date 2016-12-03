package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn){
		super();
		this.conn = conn;
	}

	public String getCreds(String username) throws SQLException{
		String sql = "SELECT ERS_USERNAME, ERS_PASSWORD FROM ERS_USERS WHERE ERS_USERNAME = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// set username in query
		stmt.setString(1, username);
		
		//grab results
		ResultSet rs = stmt.executeQuery();
		
		// get the next row
		rs.next();
		System.out.println(rs.getString("ERS_USERNAME"));
		System.out.println(rs.getString("ERS_PASSWORD"));
			
		return rs.getString("ERS_PASSWORD");
	}

}
