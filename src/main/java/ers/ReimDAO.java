package ers;

import java.util.List;

public interface ReimDAO {
	public List<Reim> getAllReims();
//	public List<Reim> getReims(String username);
	public Reim createReim(int amount, int author, int typeId, String description);
	public boolean deleteReim(int id);
	public boolean updateReim(int id);
}
