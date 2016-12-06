package ers;

public interface UserDAO {
	
	public User getUser(String username);
	public String getPassword(String username);
}
