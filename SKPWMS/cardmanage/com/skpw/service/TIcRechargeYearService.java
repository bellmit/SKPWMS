package com.skpw.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TIcRechargeYear;
import com.skpw.bean.TPsPermits;


public interface TIcRechargeYearService {
	public Page<TIcRechargeYear> findAllByPage(TIcRechargeYear tIcRechargeYear,TBasEnterprise tBasEnterprise,Date rechargeStart,Date rechargeEnd,Long rechargeYearStart,Long rechargeYearEnd, Pageable pager);

	public TIcRechargeYear findOne(String id);
	
	public TIcRechargeYear save(TIcRechargeYear tIcRechargeYear);
	
	//年度
	public TIcRechargeYear save(TIcRechargeYear tIcRechargeYear,TPsPermits tPsPermits);
	
	//周期
	public List<TIcRechargeYear> save(List<TIcRechargeYear> tIcRechargeYears,List<TPsPermits> tPsPermitss);
	
	public void update(TIcRechargeYear tIcRechargeYear);
	
	public void delete(String id);

	public void deleteList(List<String> ids);
	
	public Page<TIcRechargeYear> findczxxByqyid(TBasEnterprise tBasEnterprise,Pageable pager);
	
	public List<Map<String, Object>> findlastxkpflByqyid(int page, int rows, String qyid);
}
