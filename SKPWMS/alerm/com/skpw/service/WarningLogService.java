package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.WarningLog;

public interface WarningLogService {
	// 显示所有
	public List showWarningLog(int startRow, int rowsCount,String starttime,String endtime);

	// 分页显示
	public Page<WarningLog> showWarningLogByCondition(Pageable pageable,
			Specification<WarningLog> spec);

	// 报警记录数
	public long count(Specification<WarningLog> spec);
	
	public List showWarningLognew(int startRow, int rowsCount,String starttime,String endtime,String enterId,List<String> zzjgids);

}
