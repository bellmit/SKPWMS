package com.skpw.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skpw.bean.TPsOutWGPoll;

@Repository
public interface TPsOutWGPollRepository extends JpaRepository<TPsOutWGPoll, String>,JpaSpecificationExecutor<TPsOutWGPoll>{

	@Query("Select distinct new Map(t.tBasPollutant.fPollutantID as id,t.tBasPollutant.fPollutantName as name) From TPsOutWGPoll t Where t.tPsOutPermit.fOutPID=:fOutPID ")
	public List<Map<String, String>> distinct(@Param("fOutPID") String fOutPID);
}
