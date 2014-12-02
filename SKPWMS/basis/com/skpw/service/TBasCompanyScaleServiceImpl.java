package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasCompanyScale;
import com.skpw.repository.TBasCompanyScaleRepository;

@Service("tBasCompanyScaleService")
public class TBasCompanyScaleServiceImpl implements TBasCompanyScaleService{

	@Resource
	private TBasCompanyScaleRepository tBasCompanyScaleRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasCompanyScaleRepository.count();
	}

	@Override
	public List<TBasCompanyScale> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasCompanyScale> page = tBasCompanyScaleRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasCompanyScale tem) {
		// TODO Auto-generated method stub
		tBasCompanyScaleRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasCompanyScaleRepository.delete(id);
	}

	@Override
	public TBasCompanyScale findOne(String id) {
		// TODO Auto-generated method stub
		return tBasCompanyScaleRepository.findOne(id);
	}

	@Override
	public List<TBasCompanyScale> findAll() {
		// TODO Auto-generated method stub
		return tBasCompanyScaleRepository.findAll();
	}

}
