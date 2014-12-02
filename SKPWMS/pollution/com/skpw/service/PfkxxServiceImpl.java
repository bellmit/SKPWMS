 package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TPsOutSewagebak;
import com.skpw.repository.PfkxxRepository;

@Service("pfkxxService")
public class PfkxxServiceImpl implements PfkxxService{

	@Resource
	private PfkxxRepository pfkxxRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) pfkxxRepository.count();
	}

	@Override
	public List<TPsOutSewagebak> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TPsOutSewagebak> accounts = pfkxxRepository.findAll(pageable);
		return accounts.getContent();
	}

	@Override
	public void save(TPsOutSewagebak pfk) {
		// TODO Auto-generated method stub
		pfkxxRepository.save(pfk);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		pfkxxRepository.delete(id);
	}

}
