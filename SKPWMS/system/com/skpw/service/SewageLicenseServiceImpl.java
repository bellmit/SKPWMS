package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasSewageLicence;
import com.skpw.repository.SewageLicenseRepository;

@Service("sewageLicenseService")
public class SewageLicenseServiceImpl implements SewageLicenceService {

	@Resource
	private SewageLicenseRepository sewageLicenseRepository;

	public List<TBasSewageLicence> showTBasSewageLicence() {

		return sewageLicenseRepository.findAll();
	}

	public Page<TBasSewageLicence> showSewageLicenceListByCondition(
			Specification<TBasSewageLicence> spec, Pageable pager) {

		return sewageLicenseRepository.findAll(spec, pager);
	}

	public void saveSewageLicence(TBasSewageLicence sewageLicence) {

		sewageLicenseRepository.save(sewageLicence);
	}

	public void deleteSewageLicence(String sewageid) {
		sewageLicenseRepository.delete(sewageid);
	}

	public void updateSewageLicence(TBasSewageLicence sewageLicence) {

		sewageLicenseRepository.save(sewageLicence);
	}

	public TBasSewageLicence initUpdateSewageLicence(String sewageid) {

		return sewageLicenseRepository.findOne(sewageid);
	}

	public long count() {

		return sewageLicenseRepository.count();
	}

	@Override
	public void deleteList(List<String> ids) {
		
		for(String sewageid:ids){
			
			sewageLicenseRepository.delete(sewageid);
		}
		
		
	}

}
