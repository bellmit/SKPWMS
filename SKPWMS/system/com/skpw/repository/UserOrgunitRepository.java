package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TSysuserOrgUnit;

public interface UserOrgunitRepository  extends JpaRepository<TSysuserOrgUnit, String>{
	//通过用户查找组织
	@Query(value="select  *   from  T_SYS_UserOrgUnit  ou  where  ou.FUserID=?1",nativeQuery=true  )
	public List<TSysuserOrgUnit> findUserOrgunitByuserid(String userid);
	
	@Transactional 
	@Modifying
	@Query(value="delete   from  T_SYS_UserOrgUnit  where  FUserOrgUnitID=?1",nativeQuery=true  )
	public  void   deleteUserOrgunitByid(String ouid);
}
