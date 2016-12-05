package ers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReimDAOFacade {
	private Connection conn = null;
	
	private Connection getConnection() throws SQLException{
		conn = ServiceLocator.getERSDatabase().getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	public List<Reim> getReims() throws SQLException{
		Connection conn = getConnection();
		ReimDAO dao = ReimFactory.newReimDAO(conn);
		List<Reim> list = dao.getReims();
		conn.close();
		return list;
	}
	
	public Reim createReim(int id, int amount, int author, int typeId, String description) throws SQLException{
		Connection conn = getConnection();
		ReimDAO dao = ReimFactory.newReimDAO(conn);
		Reim reim = dao.createReim(id, amount, author, typeId, description);
		conn.commit();
		conn.close();
		return reim;
	}
	
//	public void closeConnection() throws SQLException{
//		conn.close();
//	}
}

class ReimFactory{
	public static ReimDAO newReimDAO(Connection conn){
		return new ReimDAOImpl(conn);
	}
}
