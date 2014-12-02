package com.skpw.repository;

import java.util.List;

public interface ResourceDao {

	// 获取操作URL
	public List getResourceByRoleID(String roleid);

	// 通过权限获取资源
	public List<String> getValues(String authorityid);

	public List getAuthorityid(String username);

}
