package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.WarningLog;
import com.skpw.dao.WarningLogDao;
import com.skpw.repository.WarningLogRepository;

@Service("warningLogService")
public class WarningLogServiceImpl implements WarningLogService {

	@Resource
	private WarningLogRepository warningLogRepository;

	@Resource
	private WarningLogDao warningLogDao;

	public List showWarningLog(int startRow, int rowsCount,String strattime,String endtime) {

		return warningLogDao.showWarnLog(startRow,rowsCount,strattime,endtime);
	}

	public Page<WarningLog> showWarningLogByCondition(Pageable pageable,
			Specification<WarningLog> spec) {

		return warningLogRepository.findAll(spec, pageable);
	}

	public long count(Specification<WarningLog> spec) {

		return warningLogRepository.count(spec);
	}

	@Override
	public List showWarningLognew(int startRow, int rowsCount,
			String starttime, String endtime,String enterId,List<String> zzjgids) {
		return warningLogDao.showWarnLognew(startRow, rowsCount, starttime, endtime,enterId,zzjgids);
	}

}
