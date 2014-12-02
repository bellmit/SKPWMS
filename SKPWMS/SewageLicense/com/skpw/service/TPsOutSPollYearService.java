package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutSPollYear;


public interface TPsOutSPollYearService {
	public Page<TPsOutSPollYear> findAllByPage(TPsOutSPollYear tPsOutSPollYear,Pageable pager);

	public TPsOutSPollYear findOne(String id);
	
	public TPsOutSPollYear save(TPsOutSPollYear tPsOutSPollYear, TPsOutPermit tPsOutPermit);
	
	public TPsOutSPollYear update(TPsOutSPollYear tPsOutSPollYear);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
}
