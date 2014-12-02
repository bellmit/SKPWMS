package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasEnterEnviron;
import com.skpw.repository.WryhbsxRepository;

@Service("wryhbsxService")
public class WryhbsxServiceImpl implements WryhbsxService{

	@Resource
	private WryhbsxRepository wryhbsxRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) wryhbsxRepository.count();
	}

	@Override
	public List<TBasEnterEnviron> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasEnterEnviron> pg = wryhbsxRepository.findAll(pageable);
		return pg.getContent();
	}

	@Override
	public void save(TBasEnterEnviron tee) {
		// TODO Auto-generated method stub
		wryhbsxRepository.save(tee);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		wryhbsxRepository.delete(id);
	}

	@Override
	public TBasEnterEnviron findWry(String id) {
		// TODO Auto-generated method stub
		return wryhbsxRepository.findOne(id);
	}

}
