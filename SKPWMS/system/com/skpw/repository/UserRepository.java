package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TSysRole;
import com.skpw.bean.TSysUserInfo;

public interface UserRepository extends JpaRepository<TSysUserInfo, String>,
		JpaSpecificationExecutor<TSysUserInfo> {

	@Query("from TSysUserInfo u  where u.username=?1 and u.password=?2")
	public abstract TSysUserInfo login(String username, String password);
	
	@Query(value="select r.FRoleID,r.FRoleName from T_SYS_USERINFO u,T_SYS_UserRole ur,T_SYS_Role r where u.id=ur.FUserID and ur.FRoleID=r.FRoleID and u.id=?1",nativeQuery=true)
	public abstract List<TSysRole> findRoleByUserId(String userid);
	
	
	@Query(value="delete  from  T_SYS_UserRole  where FUserID=?1 and FRoleID=?2 ",nativeQuery=true)
	public abstract void  deleteuserrole(String userid,String roleid);
	
	//通过用户名获取用户对象
	@Query("from TSysUserInfo u  where u.username=?1")
	public TSysUserInfo  findbyUsername(String username); 
	
	//通过用户查找组织
	@Query(value="select ou.FOrgUnitID   from T_SYS_USERINFO u,T_SYS_UserOrgUnit ou where   u.id=ou.FUserID  and FUserID=?1",nativeQuery=true)
	public abstract String findorgunitByUserId(String userid);
}
