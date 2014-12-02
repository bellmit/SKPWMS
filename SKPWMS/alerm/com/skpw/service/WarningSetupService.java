package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.AlermSetting;
import com.skpw.bean.WarningSetup;

public interface WarningSetupService {

	// 显示
	public List showWarningSetup();

	// 条件查询
	public Page<WarningSetup> showWarningSetupByCondition(Specification<WarningSetup> spec,Pageable pageable);

	// 保存修改

	public WarningSetup saveWarningSetup(WarningSetup warningSetup);

	// 删除

	public void delWarningSetup(String id);
	
	//查找
	public WarningSetup  findOne(String id);

	// 记录数
	public long count();

}
