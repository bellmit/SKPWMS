package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.OfflineLog;

public interface OfflineLogService {

	// 显示所有脱机历史记录
	public List showOfflineLog();

	// 分页和脱机查询脱机历史记录
	public Page<OfflineLog> showOfflineLogByCondition(
			Specification<OfflineLog> spec, Pageable pager);

	// 统计条数
	public long count(Specification<OfflineLog> spec);
	
	//查找
	public OfflineLog findOne(String id);
}
