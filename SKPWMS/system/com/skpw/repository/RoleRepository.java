package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.TSysAuthority;
import com.skpw.bean.TSysRole;

public interface RoleRepository extends JpaRepository<TSysRole, String>,JpaSpecificationExecutor<TSysRole> {
	@Query(value="select a.FAuthorityID,a.FAuthorityName from T_SYS_Role r,T_SYS_MenuAuthority a,T_SYS_AuthorityRole ra  where r.FRoleID= ra.FRoleID and  ra.FAuthorityID=a.FAuthorityID  and r.FRoleID=?1 ",nativeQuery=true)
	public List<TSysAuthority> findAuthorityByroleId(String roleid);
	
}
