package ers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
	private Connection conn;

	public UserDAOImpl(Connection conn){
		super();
		this.conn = conn;
	}

	public String getCreds(String username){
		String password = null; 
		try{
			String sql = "SELECT ERS_USERNAME, ERS_PASSWORD FROM ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			// set username in query
			stmt.setString(1, username);
			//grab results
			ResultSet rs = stmt.executeQuery();
			password = getPassword(rs);
		}catch(SQLException e){
			System.out.println("SQL broken at getCreds method");
			e.printStackTrace();
		}
		return password;
	}
	
	private String getPassword(ResultSet rs) throws SQLException{
		if(rs.next()) return rs.getString("ERS_PASSWORD");
		else return null;
	}
	
	
}
