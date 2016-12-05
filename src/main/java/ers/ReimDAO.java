package ers;

import java.util.List;

public interface ReimDAO {
	public List<Reim> getReims();
	public Reim createReim(int id, int amount, int author, int typeId, String description);
	public boolean deleteReim(int id);
	public boolean updateReim(int id);
}
