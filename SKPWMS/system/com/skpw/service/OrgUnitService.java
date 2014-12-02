package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skpw.bean.TSysorgUnit;

public interface OrgUnitService {

	// 显示所有组织

	public List<TSysorgUnit> showTSysorgUnitInfo();

	// 分页显示所有组织
	public Page<TSysorgUnit> showTSysorgUnitListByCondition(Specification<TSysorgUnit> spec, Pageable pager);

	// 添加组织
	public void saveTSysorgUnit(TSysorgUnit orgUnit);

	// 删除组织
	public void deleteTSysorgUnit(String orgid);

	// 修改组织
	public void updateTSysorgUnit(TSysorgUnit orgUnit);

	// 修改初始化
	public TSysorgUnit initUpdateTSysorgUnit(String orgid);

	// 组织记录
	public long count();
	
	//根据父节点得到子节点
	public List<TSysorgUnit> findNodesByParentId(String id);
	
	public List<TSysorgUnit> findByOrgUnitCode(String orgUnitCode);
	
	public TSysorgUnit findOne(String id);
	
	public List<TSysorgUnit> findByLongCode(String longCode);
}
