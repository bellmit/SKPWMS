package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.TBasCreditGrade;
import com.skpw.repository.TBasCreditGradeRepository;

@Service("tBasCreditGradeService")
public class TBasCreditGradeServiceImpl implements TBasCreditGradeService{

	@Resource
	private TBasCreditGradeRepository tBasCreditGradeRepository;
	
	@Override
	public List<TBasCreditGrade> findAll() {
		// TODO Auto-generated method stub
		return tBasCreditGradeRepository.findAll();
	}

}
