package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsPermits;

@Repository
public interface TPsPermitsRepository extends JpaRepository<TPsPermits, String>,JpaSpecificationExecutor<TPsPermits> {
	
	@Query("From TPsPermits t Where t.tBasEnterprise.fenterId=:fenterId And t.tBasPollutant.fPollutantID=:fPollutantID And t.fYearID=:fYearID")
	public TPsPermits findByDetails(@Param("fenterId") String fenterId,@Param("fPollutantID") String fPollutantID,@Param("fYearID") Long fYearID);

}
