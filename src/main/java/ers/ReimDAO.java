package ers;

import java.util.List;

public interface ReimDAO {
	public List<Reim> getAllReims();
	public boolean deleteReim(int id);
	public boolean updateReim(int id);
}
