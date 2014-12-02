package com.skpw.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasCity;
import com.skpw.dao.TBasCityDao;
import com.skpw.repository.TBasCityRepository;

@Service("tBasCityService")
public class TBasCityServiceImpl implements TBasCityService{

	@Resource
	private TBasCityDao tBasCityDao;
	
	@Resource
	private TBasCityRepository tBasCityRepository;
	
	@Override
	public List<Map<String, Object>> findList() {
		// TODO Auto-generated method stub
		return tBasCityDao.findList();
	}

	@Override
	public List<TBasCity> findTBasCityList(Specification<TBasCity> spf) {
		// TODO Auto-generated method stub
		return tBasCityRepository.findAll(spf);
	}

	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasCityRepository.count();
	}

	@Override
	public List<TBasCity> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasCity> page = tBasCityRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasCity tem) {
		// TODO Auto-generated method stub
		tBasCityRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasCityRepository.delete(id);
	}

	@Override
	public TBasCity findOne(String id) {
		// TODO Auto-generated method stub
		return tBasCityRepository.findOne(id);
	}

}
