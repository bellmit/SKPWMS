package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.SMAdjust;
import com.skpw.repository.SMAdjustRepository;

@Service("smAdjustService")
public class SMAdjustServiceImpl implements SMAdjustService {

	@Resource
	private SMAdjustRepository smAdjustRepository;

	public List<SMAdjust> showSMAdjustInfo() {

		return smAdjustRepository.findAll();
	}

	public Page<SMAdjust> showSMAdjustListByCondition(
			Specification<SMAdjust> spec, Pageable pager) {

		return smAdjustRepository.findAll(spec, pager);

	}

	public void saveSMAdjust(SMAdjust smadjust) {
		smAdjustRepository.save(smadjust);
	}

	public void deleteSMAdjust(String smadjustid) {
		smAdjustRepository.delete(smadjustid);

	}

	public long count(Specification<SMAdjust> spec) {

		return smAdjustRepository.count(spec);
	}

	@Override
	public SMAdjust isExist(int pollutant, int monthid) {
		
		return smAdjustRepository.isExist(pollutant, monthid);
	}

}
