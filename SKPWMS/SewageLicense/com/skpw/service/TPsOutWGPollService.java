package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsOutWGPoll;

public interface TPsOutWGPollService {

	public Page<TPsOutWGPoll> findAllByPage(TPsOutWGPoll tPsOutWGPoll,Pageable pager);
	
	public TPsOutWGPoll save(TPsOutWGPoll tPsOutWGPoll);
	
	public TPsOutWGPoll findOne(String id);
	
	public void delete(String id);
	
	public List<Map<String, String>> distinct(String fOutPID);
}
