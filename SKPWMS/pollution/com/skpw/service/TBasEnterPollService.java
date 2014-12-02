package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TBasEnterPoll;

public interface TBasEnterPollService {

	public List<TBasEnterPoll> getList(String id);
	
	//更新已有的污染因子
	public void updateAll(List<String> list, String id);

	public void save(List<TBasEnterPoll> list);
	
	public void del(String id);
	
	public void deleteByEnterId(String enterId);
	
	//通过用户查找企业下的管辖组织结构
	public List findOrgByUserid(String userid);
	
	//通过用户查找用户对应的企业
	public List<Map<String, Object>> findEnterByUserid(String userid);
	
	public List<String> findOrgIdsByUserid(String userid);
	
	public List<String> findOrgIdsByOrglongcode(List<String> flongcodes);
	
}
