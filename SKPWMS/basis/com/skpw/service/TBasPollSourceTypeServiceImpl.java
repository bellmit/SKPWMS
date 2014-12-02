package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasPollSourceType;
import com.skpw.repository.TBasPollSourceTypeRepository;

@Service("tBasPollSourceTypeService")
public class TBasPollSourceTypeServiceImpl implements TBasPollSourceTypeService{

	@Resource
	private TBasPollSourceTypeRepository tBasPollSourceTypeRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasPollSourceTypeRepository.count();
	}

	@Override
	public List<TBasPollSourceType> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasPollSourceType> page = tBasPollSourceTypeRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasPollSourceType tem) {
		// TODO Auto-generated method stub
		tBasPollSourceTypeRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasPollSourceTypeRepository.delete(id);
	}

	@Override
	public TBasPollSourceType findOne(String id) {
		// TODO Auto-generated method stub
		return tBasPollSourceTypeRepository.findOne(id);
	}

	@Override
	public List<TBasPollSourceType> findAll() {
		// TODO Auto-generated method stub
		return tBasPollSourceTypeRepository.findAll();
	}

}
