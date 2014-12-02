package com.skpw.service;

import java.util.List;

import com.skpw.bean.TSysuserOrgUnit;

public interface UserOrgunitService {
	
	
	//通过用户查找组织
	public List<TSysuserOrgUnit> findUserOrgunitByuserid(String userid);
	
	//保存用户组织关系
	public void saveUserOrgUnit(TSysuserOrgUnit tSysuserOrgUnit);
	
	//删除用户组织关系
	public void delUserOrgUnit(TSysuserOrgUnit tSysuserOrgUnit);
}
