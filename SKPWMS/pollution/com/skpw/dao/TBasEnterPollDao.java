package com.skpw.dao;

import java.util.List;
import java.util.Map;

import com.skpw.bean.TBasEnterPoll;

public interface TBasEnterPollDao {

	public List<TBasEnterPoll> getList(String id);

	public void updateAll(List<String> list,String id);
	
	//通过用户查找企业下的管辖组织结构
	public List findOrgByUserid(String userid);
	
	//通过用户id查找企业
	public List<Map<String, Object>> findEnterByUserid(String userid);
	
	public List<String> findOrgIdsByUserid(String userid);
	
	public List<String> findOrgIdsByOrglongcode(List<String> flongcodes);
	
}
