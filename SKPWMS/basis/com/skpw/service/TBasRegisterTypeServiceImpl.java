package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasRegisterType;
import com.skpw.repository.TBasRegisterTypeRepository;

@Service("tBasRegisterTypeService")
public class TBasRegisterTypeServiceImpl implements TBasRegisterTypeService{

	@Resource
	private TBasRegisterTypeRepository tBasRegisterTypeRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasRegisterTypeRepository.count();
	}

	@Override
	public List<TBasRegisterType> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasRegisterType> page = tBasRegisterTypeRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasRegisterType tem) {
		// TODO Auto-generated method stub
		tBasRegisterTypeRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasRegisterTypeRepository.delete(id);
	}

	@Override
	public TBasRegisterType findOne(String id) {
		// TODO Auto-generated method stub
		return tBasRegisterTypeRepository.findOne(id);
	}

	@Override
	public List<TBasRegisterType> findAll() {
		// TODO Auto-generated method stub
		return tBasRegisterTypeRepository.findAll();
	}

}
