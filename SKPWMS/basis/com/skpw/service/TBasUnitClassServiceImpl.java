package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasUnitClass;
import com.skpw.repository.TBasUnitClassRepository;

@Service("tBasUnitClassService")
public class TBasUnitClassServiceImpl implements TBasUnitClassService{

	@Resource
	private TBasUnitClassRepository tBasUnitClassRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasUnitClassRepository.count();
	}

	@Override
	public List<TBasUnitClass> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasUnitClass> page = tBasUnitClassRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasUnitClass tem) {
		// TODO Auto-generated method stub
		tBasUnitClassRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasUnitClassRepository.delete(id);
	}

	@Override
	public TBasUnitClass findOne(String id) {
		// TODO Auto-generated method stub
		return tBasUnitClassRepository.findOne(id);
	}

	@Override
	public List<TBasUnitClass> findAll() {
		// TODO Auto-generated method stub
		return tBasUnitClassRepository.findAll();
	}

}
