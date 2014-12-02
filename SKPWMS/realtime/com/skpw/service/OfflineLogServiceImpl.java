package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.OfflineLog;
import com.skpw.repository.OfflineLogRepositoty;

@Service("offlineLogService")
public class OfflineLogServiceImpl implements OfflineLogService {

	@Resource
	private OfflineLogRepositoty offlineLogRepositoty;

	public List showOfflineLog() {

		return offlineLogRepositoty.findAll();
	}

	public Page<OfflineLog> showOfflineLogByCondition(
			Specification<OfflineLog> spec, Pageable pager) {

		return offlineLogRepositoty.findAll(spec, pager);
	}

	public long count(Specification<OfflineLog> spec) {

		return offlineLogRepositoty.count(spec);
	}

	
	public OfflineLog findOne(String id) {
		
		return offlineLogRepositoty.findOne(id);
	}

}
