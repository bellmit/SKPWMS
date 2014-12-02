package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.WarningSetup;
import com.skpw.repository.WarningSetupRepository;

@Service("warningSetupService")
public class WarningSetupServiceImpl implements WarningSetupService {

	@Resource
	private WarningSetupRepository warningSetupRepository;

	public List showWarningSetup() {

		return warningSetupRepository.findAll();
	}

	public Page<WarningSetup> showWarningSetupByCondition(
			Specification<WarningSetup> spec, Pageable pageable) {

		return warningSetupRepository.findAll(spec, pageable);
	}

	public WarningSetup saveWarningSetup(WarningSetup warningSetup) {
		
		return warningSetupRepository.save(warningSetup);
	}

	public void delWarningSetup(String id) {

		warningSetupRepository.delete(id);
	}

	public long count() {

		return warningSetupRepository.count();
	}

	
	public WarningSetup findOne(String id) {
		
		return warningSetupRepository.findOne(id);
	}

}
