package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.Alerminfo;
import com.skpw.repository.AlermInfoRepository;

@Service("alermInfoService")
public class AlermInfoServiceImpl implements AlermInfoService {

	@Resource
	private AlermInfoRepository alermInfoRepository;

	public List showAlermInfo() {

		return alermInfoRepository.findAll();
	}

	public Page<Alerminfo> showAlermInfoByCondition(Pageable pageable,
			Specification<Alerminfo> spec) {

		return alermInfoRepository.findAll(spec, pageable);
	}

	public long count() {
		
		return alermInfoRepository.count();
	}

}
