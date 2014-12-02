package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysLog;

public interface LogService {
	
	//显示日志信息
	public List<TSysLog> showLogList();	
	
	// 条件查询日志信息
	public Page<TSysLog> showLogListByCondition(Specification<TSysLog> spec,Pageable pager);
	
	//添加日志信息
	public void addLog(TSysLog tSysLog);
	
	//统计条数
	public long count(Specification<TSysLog> spec);
}
