package com.skpw.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsOutSPoll;

@Repository
public interface TPsOutSPollRepository extends JpaRepository<TPsOutSPoll, String>,JpaSpecificationExecutor<TPsOutSPoll> {

	//查询排污许可证的主要污染物
	@Query("Select distinct new Map(t.tBasPollutant.fPollutantID as id,t.tBasPollutant.fPollutantName as name) From TPsOutSPoll t Where t.tPsOutPermit.fOutPID=:fOutPID ")
	public List<Map<String, String>> distinct(@Param("fOutPID") String fOutPID);
	
	//查询废水排放口的主要污染物
	@Query("Select distinct new Map(t.tBasPollutant.fPollutantID as id,t.tBasPollutant.fPollutantName as name) From TPsOutSPoll t Where t.tPsOutSewage.fOutSewageID=:fOutSewageID ")
	public List<Map<String, String>> findPollutantByOutSewage(@Param("fOutSewageID") String fOutSewageID);
	
	@Query("Select distinct new Map(t.tBasPollutant.fPollutantID as id,t.tBasPollutant.fPollutantName as name) From TPsOutWGPoll t Where t.tPsWasteGasOutlet.fWGOutletID=:fWGOutletID ")
	public List<Map<String, String>> findPollutantBytPsWasteGasOutlet(@Param("fWGOutletID") String fWGOutletID);
}
