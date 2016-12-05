package ers;

import java.sql.SQLException;
import java.util.List;

public class ReimCRUD {
	ReimDAOFacade facade = new ReimDAOFacade();
	
	public List<Reim> getReims() throws SQLException{
		return facade.getReims();
	}
	
	public Reim createReim(int id, int amount, int author, int typeId, String description) 
			throws SQLException{
		return facade.createReim(id, amount, author, typeId, description);
	}
	
//	public void closeConnection() throws SQLException{
//		facade.closeConnection();
//	}
	
//	@Override
//	protected void finalize() throws Throwable{
//		try{
//			System.out.println("Closing Connection");
//			facade.closeConnection();
//		}catch(Throwable t){
//			throw t;
//		}finally{
//			System.out.println("Calling finalize of SuperClass");
//			super.finalize();
//		}
//	}
	
	public static void main(String[] args) throws SQLException {
		ReimCRUD crud = new ReimCRUD();
		System.out.println(crud.getReims());
		System.out.println(crud.createReim(5, 82, 4, 1, "Travel"));
//		crud.closeConnection();
		System.out.println("Complete!");
	}
}