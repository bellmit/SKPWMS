package com.skpw.service;

import com.skpw.bean.UserAttempts;

public interface UserDetailsService {

	public void updateFailAttempts(String username);

	public void resetFailAttempts(String username);

	public UserAttempts getUserAttempts(String username);

}
