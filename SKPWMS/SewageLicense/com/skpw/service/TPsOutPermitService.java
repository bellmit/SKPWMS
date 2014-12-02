package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsOutPermit;


public interface TPsOutPermitService {
	public Page<TPsOutPermit> findAllByPage(TPsOutPermit tPsOutPermit,Pageable pager);

	public TPsOutPermit findOne(String id);
	
	//查找该企业id当前时间有效的排污许可证
	public TPsOutPermit findByEnterpriseId(String id);
	
	public TPsOutPermit save(TPsOutPermit tPsOutPermit);
	
	public void update(TPsOutPermit tPsOutPermit);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
	
	public List<TPsOutPermit> findPermitByEnterId(String enterId);
}
