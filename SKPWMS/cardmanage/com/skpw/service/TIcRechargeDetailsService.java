package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TIcRechargeDetails;


public interface TIcRechargeDetailsService {
	public Page<TIcRechargeDetails> findAllByPage(TIcRechargeDetails tIcRechargeDetails,Pageable pager);

	public TIcRechargeDetails findOne(String id);
	
	public TIcRechargeDetails save(TIcRechargeDetails tIcRechargeDetails);
	
	public void update(TIcRechargeDetails tIcRechargeDetails);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
}
