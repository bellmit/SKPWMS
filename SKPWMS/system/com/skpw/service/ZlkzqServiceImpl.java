package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasZlkzq;
import com.skpw.repository.ZlkzqRepository;

@Service("zlkzqService")
public class ZlkzqServiceImpl implements ZlkzqService{

	@Resource
	private ZlkzqRepository zlkzqRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) zlkzqRepository.count();
	}

	@Override
	public List<TBasZlkzq> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasZlkzq> accounts = zlkzqRepository.findAll(pageable);
		return accounts.getContent();
	}

	@Override
	public void save(TBasZlkzq pfk) {
		// TODO Auto-generated method stub
		zlkzqRepository.save(pfk);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		zlkzqRepository.delete(id);
	}

}
