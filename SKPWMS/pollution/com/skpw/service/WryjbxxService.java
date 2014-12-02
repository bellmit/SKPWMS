package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasEnterprise;

public interface WryjbxxService {

	//public List<TBasEnterprise> findPollutionBasInfo(Specification<UserInfo> spec,Pageable pager);
	
	//分页查询
	public List<TBasEnterprise> findPollutionBasInfo(Specification<TBasEnterprise> spf, Pageable pageable);
	
	//条件查询
	public List<TBasEnterprise> findPollutionBasInfoByCondition(Specification<TBasEnterprise> spf);
	
	//查询数据条数
	public int findPollutionBasCount();
	
	//删除
	public void del(String id);
	
	//保存
	public void save(TBasEnterprise tbe);
	
	//根据id查询污染源基本信息
	public TBasEnterprise findWry(String id);
	
	//查询所有的污染源信息
	public List<TBasEnterprise> findAll();
	
	public Page<TBasEnterprise> findPollutionBasInfonew(Specification<TBasEnterprise> spf, Pageable pageable);
	
	public List<String> findenterIdsByzzjgid(List<String> zzjgids);
	
	public List<TBasEnterprise> findqylistByzzjgid(List<String> zzjgs);
}
