package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TIcRecharge;


public interface TIcRechargeService {
	public Page<TIcRecharge> findAllByPage(TIcRecharge tIcRecharge,Pageable pager);

	public TIcRecharge findOne(String id);
	
	public TIcRecharge findOneByFRechargeNo(String fRechargeNo);
	
	public TIcRecharge save(TIcRecharge tIcRecharge);
	
	public void update(TIcRecharge tIcRecharge);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
}
