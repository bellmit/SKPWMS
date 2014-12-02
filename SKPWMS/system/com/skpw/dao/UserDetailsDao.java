package com.skpw.dao;

import com.skpw.bean.UserAttempts;

public interface UserDetailsDao {

	public void updateFailAttempts(String username);

	public void resetFailAttempts(String username);

	public UserAttempts getUserAttempts(String username);
	
	
}
