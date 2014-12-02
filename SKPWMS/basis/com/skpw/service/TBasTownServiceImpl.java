package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasTown;
import com.skpw.repository.TBasTownRepository;

@Service("tBasTownService")
public class TBasTownServiceImpl  implements TBasTownService{

	@Resource
	private TBasTownRepository tBasTownRepository;
	
	@Override
	public List<TBasTown> getList(Specification<TBasTown> spf) {
		// TODO Auto-generated method stub
		return tBasTownRepository.findAll(spf);
	}

	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasTownRepository.count();
	}

	@Override
	public List<TBasTown> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasTown> page = tBasTownRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasTown tem) {
		// TODO Auto-generated method stub
		tBasTownRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasTownRepository.delete(id);
	}

	@Override
	public TBasTown findOne(String id) {
		// TODO Auto-generated method stub
		return tBasTownRepository.findOne(id);
	}

}
