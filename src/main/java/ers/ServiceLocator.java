package ers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServiceLocator {
	
	private static DataSource ers;
	private static Properties env;
	
	//	Debug
//	public static void main(String[] args) {
//		DataSource ds = ServiceLocator.getERSDatabase();
//		if (ds != null)System.out.println("Works: " + ds);
//		else System.out.println("It does not work T_T");
//	}
	
	static{
		// initialize stream with file location
		InputStream stream = ServiceLocator.class.getClassLoader()
				.getResourceAsStream("jndi.properties");
		// initialize properties 
		env = new Properties();
		try{
			env.load(stream);
		}catch(IOException e){
			System.out.println("Stream not found!");
		}
	}
	
	// checks to see if ers is null
	public synchronized static DataSource getERSDatabase(){
		if(ers == null)
			ers = lookUpERS();
		return ers;
	}
	
	private static DataSource lookUpERS(){
		try{
			Context ctxt = new InitialContext(env);
			DataSource ds = (DataSource) ctxt.lookup(env.getProperty("ersdb"));
			return ds;
		}catch(NamingException e){
			System.out.println("Could not connect to the datasource.");
			e.printStackTrace();
			return null;
		}
	}
}
