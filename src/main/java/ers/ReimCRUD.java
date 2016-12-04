package ers;

import java.sql.Connection;
import java.util.List;

public class ReimCRUD {

	public List<Reim> getAllReim(){
		
	}
	
}

class ReimFactory{
	public static ReimDAO newReimDAO(Connection conn){
		return new ReimDAOImpl(conn);
	}
}