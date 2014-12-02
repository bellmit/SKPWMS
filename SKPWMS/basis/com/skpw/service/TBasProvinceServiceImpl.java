package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasProvince;
import com.skpw.repository.TBasProvinceRepository;

@Service("tBasProvinceService")
public class TBasProvinceServiceImpl implements TBasProvinceService{

	@Resource
	private TBasProvinceRepository tBasProvinceRepository;
	
	@Override
	public List<TBasProvince> getList(Specification<TBasProvince> spf) {
		// TODO Auto-generated method stub
		return tBasProvinceRepository.findAll(spf);
	}

	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasProvinceRepository.count();
	}

	@Override
	public List<TBasProvince> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasProvince> page = tBasProvinceRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasProvince tem) {
		// TODO Auto-generated method stub
		tBasProvinceRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasProvinceRepository.delete(id);
	}

	@Override
	public TBasProvince findOne(String id) {
		// TODO Auto-generated method stub
		return tBasProvinceRepository.findOne(id);
	}

}
