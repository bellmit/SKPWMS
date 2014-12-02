package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasEnterManagement;
import com.skpw.repository.WryglsxRepository;

@Service("wryglsxService")
public class WryglsxServiceImpl implements WryglsxService{

	@Resource
	private WryglsxRepository wryglsxRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) wryglsxRepository.count();
	}

	@Override
	public List<TBasEnterManagement> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasEnterManagement> pg = wryglsxRepository.findAll(pageable);
		return pg.getContent();
	}

	@Override
	public void save(TBasEnterManagement tem) {
		// TODO Auto-generated method stub
		wryglsxRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		wryglsxRepository.delete(id);
	}

	@Override
	public TBasEnterManagement findWry(String id) {
		// TODO Auto-generated method stub
		return wryglsxRepository.findOne(id);
	}

}
