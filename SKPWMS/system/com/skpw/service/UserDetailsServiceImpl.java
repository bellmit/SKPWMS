package com.skpw.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.UserAttempts;
import com.skpw.dao.UserDetailsDao;
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Resource
	private UserDetailsDao userDetailsDao;
	
	public void updateFailAttempts(String username) {
		userDetailsDao.updateFailAttempts(username);
		
	}

	@Override
	public void resetFailAttempts(String username) {
		userDetailsDao.resetFailAttempts(username);
		
	}

	@Override
	public UserAttempts getUserAttempts(String username) {
		
		return userDetailsDao.getUserAttempts(username);
	}

}
