package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasCounty;
import com.skpw.repository.TBasCountyRepository;

@Service("tBasCountyService")
public class TBasCountyServiceImpl implements TBasCountyService{

	@Resource
	private TBasCountyRepository tBasCountyRepository;
	
	@Override
	public List<TBasCounty> getList(Specification<TBasCounty> spf) {
		// TODO Auto-generated method stub
		return tBasCountyRepository.findAll(spf);
	}

	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasCountyRepository.count();
	}

	@Override
	public List<TBasCounty> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasCounty> page = tBasCountyRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasCounty tem) {
		// TODO Auto-generated method stub
		tBasCountyRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasCountyRepository.delete(id);
	}

	@Override
	public TBasCounty findOne(String id) {
		// TODO Auto-generated method stub
		return tBasCountyRepository.findOne(id);
	}

	@Override
	public List<TBasCounty> findCountyForCity(String id) {
		// TODO Auto-generated method stub
		//设置条件
		TBasCounty tbc = new TBasCounty();
		//设置市级行政区ID为深圳市的id
		tbc.setFcityId(id);
		Specification<TBasCounty> spf = TBasCountySpecs.getOneForSearch(tbc);
		List<TBasCounty> list = tBasCountyRepository.findAll(spf);
		return list;
	}

	@Override
	public List<TBasCounty> findCountryByAll() {
		
		return tBasCountyRepository.findCountryByAll();
	}

}
