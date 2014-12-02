package com.skpw.service;

import java.util.List;

import com.skpw.bean.TSysUserrole;

public interface RoleUserService {

	public List<TSysUserrole> findRoleUserByUserid(String userid);

	public void saveRoleUser(TSysUserrole userrole);

	public void deleteRoleUser(TSysUserrole userrole);
}
