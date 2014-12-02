package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skpw.bean.TSysorgUnit;

public interface OrgUnitRepository extends JpaRepository<TSysorgUnit, String>,JpaSpecificationExecutor<TSysorgUnit>
{	
	@Query("from TSysorgUnit as t where t.parentOrgUnit.id=:id")
	public List<TSysorgUnit> findNodesByParentId(@Param("id")String id);
	
	@Query("from TSysorgUnit as t where t.orgUnitCode=:orgUnitCode")
	public List<TSysorgUnit> findByOrgUnitCode(@Param("orgUnitCode") String orgUnitCode);
	
	@Query("from TSysorgUnit where longCode like :longCode")
	public List<TSysorgUnit> findByLongCode(@Param("longCode") String longCode);
}
