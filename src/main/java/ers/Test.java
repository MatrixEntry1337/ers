package ers;

import java.sql.SQLException;
import java.util.List;

public class Test {
	
	public static void main(String[] args) throws SQLException {
		Facade facade = new Facade();
		System.out.println(facade.getAllReims());
//		System.out.println(facade.createReim(35, 4, 2, "Food"));
		System.out.println(facade.login("thanks", "password1"));
		System.out.println("Complete!");
	}
}