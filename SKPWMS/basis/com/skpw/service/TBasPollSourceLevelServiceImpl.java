package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasPollSourceLevel;
import com.skpw.repository.TBasPollSourceLevelRepository;

@Service("TBasPollSourceLevelService")
public class TBasPollSourceLevelServiceImpl implements TBasPollSourceLevelService{
	@Resource
	private TBasPollSourceLevelRepository tBasPollSourceLevelRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasPollSourceLevelRepository.count();
	}

	@Override
	public List<TBasPollSourceLevel> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasPollSourceLevel> page = tBasPollSourceLevelRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasPollSourceLevel tem) {
		// TODO Auto-generated method stub
		tBasPollSourceLevelRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasPollSourceLevelRepository.delete(id);
	}

	@Override
	public TBasPollSourceLevel findOne(String id) {
		// TODO Auto-generated method stub
		return tBasPollSourceLevelRepository.findOne(id);
	}

	@Override
	public List<TBasPollSourceLevel> findAll() {
		// TODO Auto-generated method stub
		return tBasPollSourceLevelRepository.findAll();
	}
}
