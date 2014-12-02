package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TSysAuthorityRole;


public interface RoleAuthorityRepository  extends JpaRepository<TSysAuthorityRole,String>{
	
	/** 
	 * 删除角色权限中间表
	 */
	@Transactional 
	@Modifying
	@Query("delete from TSysAuthorityRole t1  where t1.role.id=:roleid and t1.authority.id=:authorityid")
	public void deleteAuthorityRole(@Param("roleid")String roleid,@Param("authorityid")String authorityid);
	
	@Query(value="select *  from  T_SYS_AuthorityRole ra  where  ra.FRoleID=?1 ",nativeQuery=true)
	public List<TSysAuthorityRole> findAuthorityByroleid(String roleid);
}
