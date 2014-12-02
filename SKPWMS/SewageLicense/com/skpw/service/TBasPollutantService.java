package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TBasPollutant;
import com.skpw.bean.TPsOutSPoll;
import com.skpw.bean.TPsOutWGPoll;


public interface TBasPollutantService {
	
	//查找排污口主要污染物
	public List<Map<String, String>> findAllByOutSPoll(TPsOutSPoll tPsOutSPoll);
	
	public List<TBasPollutant> findAll(TBasPollutant tBasPollutant);
	
	public Page<TBasPollutant> findAllByPage(TBasPollutant tBasPollutant,Pageable pager);

	public TBasPollutant findOne(String id);
	
	public TBasPollutant save(TBasPollutant tBasPollutant);
	
	public void update(TBasPollutant tBasPollutant);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
	
	public List<Map<String, String>> findAllByTPsOutWGPoll(TPsOutWGPoll tPsOutWGPoll);
}
