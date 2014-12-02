package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasIndustryType;
import com.skpw.bean.TreeBean;
import com.skpw.repository.TBasIndustryTypeRepository;

@Service("tBasIndustryTypeService")
public class TBasIndustryTypeServiceImpl implements TBasIndustryTypeService{

	@Resource
	private TBasIndustryTypeRepository tBasIndustryTypeRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasIndustryTypeRepository.count();
	}

	@Override
	public List<TBasIndustryType> findByPage(Specification<TBasIndustryType> spf, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasIndustryType> page = tBasIndustryTypeRepository.findAll(spf, pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasIndustryType tem) {
		// TODO Auto-generated method stub
		tBasIndustryTypeRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasIndustryTypeRepository.delete(id);
	}

	@Override
	public List<TBasIndustryType> findAllOrderByID() {
		// TODO Auto-generated method stub
		List<TBasIndustryType> list = tBasIndustryTypeRepository.findAllOrderByID();
		return list;
	}

}
