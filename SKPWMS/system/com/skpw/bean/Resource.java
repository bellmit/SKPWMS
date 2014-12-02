package com.skpw.bean;

/**
 * @author hjy
 * 封装资源表
 */
public class Resource {

	private String  roleid;//角色id
	
	private String  rolename;//角色名称
	
	private String authorityname;//权限名称
	
	private String url;//资源URL
	
	
	public Resource() {
	}

	public Resource(String roleid, String rolename, String authorityname,
			String url) {
		this.roleid = roleid;
		this.rolename = rolename;
		this.authorityname = authorityname;
		this.url = url;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getAuthorityname() {
		return authorityname;
	}

	public void setAuthorityname(String authorityname) {
		this.authorityname = authorityname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
