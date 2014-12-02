package com.skpw.repository;

import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsOutPermit;

@Repository
public interface TPsOutPermitRepository extends JpaRepository<TPsOutPermit, String>,JpaSpecificationExecutor<TPsOutPermit> {
	@Query("From TPsOutPermit t Where t.tBasEnterprise.fenterId=:fenterId And t.fVaildDate<=:date And t.fEndDate>=:date" )
	public TPsOutPermit findByEnterpriseId(@Param("fenterId") String fenterId,@Param("date") Date date);
	@Query("from TPsOutPermit as t where t.tBasEnterprise.fenterId=:enterId")
	public List<TPsOutPermit> findPermitByEnterId(@Param("enterId")String enterId);
}
