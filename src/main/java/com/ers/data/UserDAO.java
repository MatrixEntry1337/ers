package com.ers.data;

import com.ers.beans.User;

interface UserDAO {
	
	User getUser(String username);
	String getPassword(String username);
}
