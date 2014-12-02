package com.skpw.securitymanager;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserSecurity extends User {
	
	private String id;		//用户唯一标识符

	public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public UserSecurity(String id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;
	
	public String toString() {
		return "class: UserSecurity\n" + "id: " + getId() + "; username: " + getUsername();
	}
}
