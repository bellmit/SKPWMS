package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TSysUserrole;

public interface UserRoleRepository  extends  JpaRepository<TSysUserrole, String> {
	@Transactional 
	@Modifying
	@Query("delete from TSysUserrole t1  where t1.userInfo.id=:userid and t1.role.id=:roleid")
	public void deleteUserRole(@Param("userid")String userid,@Param("roleid")String roleid);
	
	
	@Query(value="select *  from T_SYS_UserRole t  where  t.FUserID=?1",nativeQuery=true)
	public   List<TSysUserrole>  findRoleUserByUserid(String userid);
			
}
