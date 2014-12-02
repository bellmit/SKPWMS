package com.skpw.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.skpw.bean.TBasEnterprise;

public interface WryjbxxRepository extends JpaRepository<TBasEnterprise, String>,JpaSpecificationExecutor<TBasEnterprise>,PagingAndSortingRepository<TBasEnterprise, String>{

	@Query("select fenterId from TBasEnterprise where forgUnitId in (?1)")
	public List<String> findenterIdsByzzjgid(List<String> zzjgids);
	
	@Query(" from TBasEnterprise where forgUnitId in (?1)")
	public List<TBasEnterprise> findqylistByzzjgid(List<String> zzjgs);
}
