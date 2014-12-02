package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsPermits;


public interface TPsPermitsService {
	public Page<TPsPermits> findAllByPage(TPsPermits tPsPermits,Pageable pager);

	public TPsPermits findOne(String id);
	
	public TPsPermits findByDetails(String fenterId,String fPollutantID,Long fYearID);
	
	public TPsPermits save(TPsPermits tPsPermits);
	
	public void update(TPsPermits tPsPermits);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
}
