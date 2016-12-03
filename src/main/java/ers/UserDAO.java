package ers;

public interface UserDAO {
	
	/**
	 * 
	 * @param username - provide username of user credentials you wish to find
	 * @return - the credentials of user found
	 */
	public String getCreds(String username);
}
