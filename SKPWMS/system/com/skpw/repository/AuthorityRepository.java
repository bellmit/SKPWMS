package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.TSysAuthority;

public interface AuthorityRepository extends JpaRepository<TSysAuthority, String>,JpaSpecificationExecutor<TSysAuthority> {
	
	@Query("select id  from TSysAuthority")
	public List<String> getIds();
}
