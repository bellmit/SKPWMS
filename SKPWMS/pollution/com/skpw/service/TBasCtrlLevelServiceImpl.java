package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TBasCtrlLevel;
import com.skpw.repository.TBasCtrlLevelRepository;

@Service("tBasCtrlLevelService")
public class TBasCtrlLevelServiceImpl implements TBasCtrlLevelService{

	@Resource
	private TBasCtrlLevelRepository tBasCtrlLevelRepository;
	
	@Override
	public List<TBasCtrlLevel> findAll() {
		// TODO Auto-generated method stub
		return tBasCtrlLevelRepository.findAll();
	}

}
