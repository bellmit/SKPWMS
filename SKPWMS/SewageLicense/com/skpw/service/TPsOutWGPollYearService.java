package com.skpw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutWGPollYear;

public interface TPsOutWGPollYearService {

	public Page<TPsOutWGPollYear> findAllByPage(TPsOutWGPollYear tPsOutWGPollYear, Pageable pager);
	
	public TPsOutWGPollYear save(TPsOutWGPollYear tPsOutWGPollYear,TPsOutPermit tPsOutPermit);
	
	public TPsOutWGPollYear findOne(String id);
	
	public void delete(String id);
}
