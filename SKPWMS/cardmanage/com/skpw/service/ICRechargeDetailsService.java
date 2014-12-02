package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TICRechargeDetailsBak;

public interface ICRechargeDetailsService {
	
	//保存充值信息
	public void saveRechargeDetails(TICRechargeDetailsBak icrRechargeDetails);
	
	//显示所有的充值信息
	public List<TICRechargeDetailsBak> showRechargeDetails();
	
	//条件查询充值信息
	
	public Page<TICRechargeDetailsBak> showRechargeDetails(Specification<TICRechargeDetailsBak> spec,Pageable pager);

}
