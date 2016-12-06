package com.ers.data;

import java.util.List;

import com.ers.beans.Reim;
import com.ers.beans.User;

interface ReimDAO {
	List<Reim> getAllReims();
//	public List<Reim> getReims(User author);
	Reim createReim(User author, int amount, int typeId, String description);
	boolean deleteReim(int id);
	boolean updateReim(int id);
}
