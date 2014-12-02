package com.skpw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.SMAdjust;

public interface SMAdjustRepository extends JpaRepository<SMAdjust, String>,
		JpaSpecificationExecutor<SMAdjust> {

	@Query(value = "select *   from  T_RT_SewageMonthAdjust  where   FPollutantID=?1  and FMonthID=?2", nativeQuery = true)
	public SMAdjust isExist(int pollutant, int monthid);
}
