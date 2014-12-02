package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsOutSPoll;


public interface TPsOutSPollService {
	public Page<TPsOutSPoll> findAllByPage(TPsOutSPoll tPsOutSPoll,Pageable pager);

	public TPsOutSPoll findOne(String id);
	
	public TPsOutSPoll save(TPsOutSPoll tPsOutSPoll);
	
	public void update(TPsOutSPoll tPsOutSPoll);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
	
	public List<Map<String, String>> distinct(String fOutPID);
}
