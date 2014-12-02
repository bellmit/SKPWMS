package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TICRechargeDetailsBak;
import com.skpw.repository.ICRechargeDetailsRepository;

@Service("icRechargeDetailsService")
public class ICRechargeDetailsServiceImpl implements ICRechargeDetailsService {

	@Resource
	private ICRechargeDetailsRepository icRechargeDetailsRepository;

	public void saveRechargeDetails(TICRechargeDetailsBak icRechargeDetails) {

		icRechargeDetailsRepository.save(icRechargeDetails);
	}

	
	public List showRechargeDetails() {
		
		return icRechargeDetailsRepository.findAll();
	}

	
	public Page<TICRechargeDetailsBak> showRechargeDetails(Specification<TICRechargeDetailsBak> spec,
			Pageable pager) {
		
		return icRechargeDetailsRepository.findAll(spec, pager);
	}

}
