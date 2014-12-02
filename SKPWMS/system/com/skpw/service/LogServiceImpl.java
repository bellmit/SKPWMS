package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysLog;
import com.skpw.bean.TSysUserInfo;
import com.skpw.common.BrowserType;
import com.skpw.common.BrowserUtils;
import com.skpw.common.ContextHolderUtils;
import com.skpw.common.ConvertUtils;
import com.skpw.common.DateUtil;
import com.skpw.repository.LogRepository;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Resource
	private LogRepository logRepository;

	public List<TSysLog> showLogList() {

		return logRepository.findAll();
	}

	
	public Page<TSysLog> showSewageLicenceListByCondition(
			Specification<TSysLog> spec, Pageable pager) {

		return logRepository.findAll(spec, pager);
	}

	public void addLog(TSysLog tSysLog) {
		
		HttpServletRequest request = ContextHolderUtils.getRequest();
		tSysLog.setBroswer(BrowserUtils.checkBrowse(request));
		tSysLog.setNote(ConvertUtils.getIp());
//		TSysUserInfo userInfo = new TSysUserInfo();
//		userInfo.setUsername("aa");
//		tSysLog.settSysUserInfo(userInfo);
		tSysLog.setOperatetime(DateUtil.gettimestamp().toString());
		logRepository.save(tSysLog);

	}

	
	public Page<TSysLog> showLogListByCondition(Specification<TSysLog> spec,
			Pageable pager) {
		
		return logRepository.findAll(spec, pager);
	}

	
	public long count(Specification<TSysLog> spec) {
		
		
		
		return logRepository.count(spec);
	}

}
