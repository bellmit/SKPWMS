package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasOutWhere;
import com.skpw.repository.TBasOutWhereRepository;

@Service("tBasOutWhereService")
public class TBasOutWhereServiceImpl implements TBasOutWhereService{

	@Resource
	private TBasOutWhereRepository tBasOutWhereRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasOutWhereRepository.count();
	}

	@Override
	public List<TBasOutWhere> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasOutWhere> page = tBasOutWhereRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasOutWhere tem) {
		// TODO Auto-generated method stub
		tBasOutWhereRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasOutWhereRepository.delete(id);
	}

	@Override
	public TBasOutWhere findOne(String id) {
		// TODO Auto-generated method stub
		return tBasOutWhereRepository.findOne(id);
	}

	@Override
	public List<TBasOutWhere> findAll() {
		// TODO Auto-generated method stub
		return tBasOutWhereRepository.findAll();
	}

}
