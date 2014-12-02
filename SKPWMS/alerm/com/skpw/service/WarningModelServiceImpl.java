package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.repository.WarningModelRepository;

@Service("warningModelService")
public class WarningModelServiceImpl implements  WarningModelService {
	
	@Resource
	private WarningModelRepository warningModelRepository;
	
	public List showWarningModel() {
		
		return warningModelRepository.findAll();
	}
	
	

}
