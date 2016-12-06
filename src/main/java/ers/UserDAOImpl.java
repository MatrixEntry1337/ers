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
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("ERS_USERS_ID"));
				user.setUsername(username);
				user.setPassword(rs.getString("ERS_PASSWORD"));
				user.setLastName(rs.getString("USER_LAST_NAME"));
				user.setFirstName(rs.getString("USER_FIRST_NAME"));
				user.setRole(rs.getInt("USER_ROLE_ID"), rs.getString("USER_ROLE"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
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
