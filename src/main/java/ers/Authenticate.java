package ers;

import java.sql.Connection;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class Authenticate {

	private String hashPassword(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	private boolean validatePassword(String password, String hashedPassword){
		if(hashedPassword == null) return false;
		return BCrypt.checkpw(password, hashedPassword);
	}

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(true);
		return conn;
	}

	public boolean authenticate(String username, String password) throws SQLException{

		// validate login
		username = username.trim();
		password = password.trim();

		if(username.length() > 0 && password.length() > 0){
			Connection conn = getConnection();
			ReimDAO dao = Factory.newReimDAO(conn);
			String hashedPassword = new UserDAOImpl(conn).getCreds(username);
			conn.close();
			return validatePassword(password, hashedPassword);
		}
		else return false;
	}

	public static void main(String[] args) throws SQLException {
		Authenticate testAuth = new Authenticate();
		String username = "kchangfatt";
		String password = "password1";
		if(testAuth.authenticate(username, password))
			System.out.println("User entered the correct credentials");
		else System.out.println("User entered the wrong credentials");
	}
	
	static class Factory{
		public static ReimDAO newReimDAO(Connection conn){
			return new ReimDAOImpl(conn);
		}
	}
}
