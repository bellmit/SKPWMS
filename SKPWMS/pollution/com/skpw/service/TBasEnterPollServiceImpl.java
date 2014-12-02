package com.skpw.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TBasEnterPoll;
import com.skpw.dao.TBasEnterPollDao;
import com.skpw.repository.TBasEnterPollRepository;

@Service("tBasEnterPollService")
public class TBasEnterPollServiceImpl implements TBasEnterPollService{

	@Resource
	private TBasEnterPollDao tBasEnterPollDao;
	
	@Resource
	private TBasEnterPollRepository tBasEnterPollRepository;

	@Override
	public List<TBasEnterPoll> getList(String id) {
		// TODO Auto-generated method stub
		return tBasEnterPollDao.getList(id);
	}


	@Override
	public void updateAll(List<String> list, String id) {
		// TODO Auto-generated method stub
		tBasEnterPollDao.updateAll(list, id);
	}


	@Override
	public void save(List<TBasEnterPoll> list) {
		// TODO Auto-generated method stub
		for(TBasEnterPoll tep:list){
			tBasEnterPollRepository.save(tep);
		}
	}


	public void del(String id)
	{
		tBasEnterPollRepository.delete(id);
	}


	public void deleteByEnterId(String enterId)
	{
		tBasEnterPollRepository.deleteByEnterId(enterId);
	}


	
	public List findOrgByUserid(String userid) {
		
		return tBasEnterPollDao.findOrgByUserid(userid);
	}

	public List<Map<String, Object>> findEnterByUserid(String userid) {
		
		return tBasEnterPollDao.findEnterByUserid(userid);
	}


	@Override
	public List<String> findOrgIdsByUserid(String userid) {
		return tBasEnterPollDao.findOrgIdsByUserid(userid);
	}


	@Override
	public List<String> findOrgIdsByOrglongcode(List<String> flongcodes) {
		return tBasEnterPollDao.findOrgIdsByOrglongcode(flongcodes);
	}

}
