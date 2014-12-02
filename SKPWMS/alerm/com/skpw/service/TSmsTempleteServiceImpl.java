package com.skpw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSmsTemplete;
import com.skpw.repository.TSmsTempleteRepository;

@Service
public class TSmsTempleteServiceImpl implements TSmsTempleteService{
	
	@Autowired
	private TSmsTempleteRepository tSmsTempleteRepository;

	@Override
	public Page<TSmsTemplete> findAllByPage(TSmsTemplete tSmsTemplete,
			Pageable pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TSmsTemplete findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TSmsTemplete> findAll() {
		return tSmsTempleteRepository.findAll();
	}

	@Override
	public TSmsTemplete save(TSmsTemplete tSmsTemplete) {
		return tSmsTempleteRepository.save(tSmsTemplete);
	}

}
