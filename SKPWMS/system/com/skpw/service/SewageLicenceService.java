package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasSewageLicence;

public interface SewageLicenceService {
	// 显示所有排污许可证
	public List<TBasSewageLicence> showTBasSewageLicence();

	// 分页显示所有排污许可证
	public Page<TBasSewageLicence> showSewageLicenceListByCondition(Specification<TBasSewageLicence> spec,Pageable pager);

	// 添加排污许可证
	public void saveSewageLicence(TBasSewageLicence sewageLicence);

	// 删除排污许可证
	public void deleteSewageLicence(String sewageid);
	//批量删除排污许可证
	public void deleteList(List<String> ids);
	// 修改排污许可证
	public void updateSewageLicence(TBasSewageLicence sewageLicence);

	// 修改初始化
	public TBasSewageLicence initUpdateSewageLicence(String sewageid);

	// 排污许可证记录
	public long count();
}
