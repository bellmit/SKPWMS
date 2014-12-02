package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.dao.PolltantYearDetailDao;

@Service("polltantYearDetailService")
public class PolltantYearDetailServiceImpl implements PolltantYearDetailService {

	@Resource
	private PolltantYearDetailDao polltantYearDetailDao;

	public List showPolltantYeayDetail(String foutpid) {

		return polltantYearDetailDao.showPolltantYeayDetail(foutpid);
	}

}
