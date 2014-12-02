package com.skpw.service;

import java.util.List;

import com.skpw.bean.TSysAuthorityRole;
import com.skpw.bean.TSysuserOrgUnit;

public interface RoleAuthorityService {
	
	List<TSysAuthorityRole> findAuthorityByroleid(String roleid);
	
	//保存角色权限
	public void saveRoleAuthority(TSysAuthorityRole authorityRole);
	//删除角色权限
	public void delRoleAuthority(TSysAuthorityRole authorityRole);
	
	//根据角色id和权限id删除角色权限
	public void deleteAuthorityRole(String roleid,String authorityid);

}
