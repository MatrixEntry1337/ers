package ers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class Facade {
	private Connection conn = null;
	
	private Connection getConnection() throws SQLException{
		conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	public List<Reim> getAllReims() throws SQLException{
		Connection conn = getConnection();
		ReimDAO dao = ReimFactory.newReimDAO(conn);
		List<Reim> list = dao.getAllReims();
		conn.close();
		return list;
	}
	
	public Reim createReim(int amount, int author, int typeId, String description) throws SQLException{
		Connection conn = getConnection();
		ReimDAO dao = ReimFactory.newReimDAO(conn);
		Reim reim = dao.createReim(amount, author, typeId, description);
		conn.commit();
		conn.close();
		return reim;
	}
	
	private String hashPassword(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	private boolean validatePassword(String password, String hashedPassword){
		if(hashedPassword == null) return false;
		return BCrypt.checkpw(password, hashedPassword);
	}

	public User login(String username, String password) throws SQLException{
		// validate login
		username = username.trim();
		password = password.trim();

		User user = null;
		
		if(username.length() > 0 && password.length() > 0){
			Connection conn = getConnection();
			UserDAO dao = UserFactory.newUserDAO(conn);
			String hashedPassword = dao.getPassword(username);
			if(validatePassword(password, hashedPassword)){
				System.out.println("User login successful");
				user = dao.getUser(username);
			}
			else System.out.println("User login failed");
		}
		return user;
	}
}

class ReimFactory{
	public static ReimDAO newReimDAO(Connection conn){
		return new ReimDAOImpl(conn);
	}
}

class UserFactory{
	public static UserDAO newUserDAO(Connection conn){
		return new UserDAOImpl(conn);
	}
}
