package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TBasBasin;
import com.skpw.repository.TBasBasinRepository;

@Service("tBasBasinService")
public class TBasBasinServiceImpl implements TBasBasinService{

	@Resource
	private TBasBasinRepository tBasBasinRepository;
	
	@Override
	public List<TBasBasin> findAll() {
		// TODO Auto-generated method stub
		return tBasBasinRepository.findAll();
	}

}
