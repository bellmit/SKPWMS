package com.skpw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skpw.bean.TRtFacility;

public interface TRtFacilityRepositoty extends JpaRepository<TRtFacility, Integer>,JpaSpecificationExecutor<TRtFacility>{

	@Query(value="select f.FFacilityID,f.FFacilityName from  T_RT_Facility f,T_TC_Controler c where f.FEnterID=c.FEnterID  and  c.fid=?1",nativeQuery=true)
	public List findFaciltityByControlid(int fctrlid);
}
