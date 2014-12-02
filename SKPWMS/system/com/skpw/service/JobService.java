package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysjob;

public interface JobService {

	// 显示所有职位
	public List<TSysjob> showJobInfo();

	// 分页显示所有职位
	public Page<TSysjob> showJobListByCondition(Specification<TSysjob> spec,
			Pageable pager);

	// 添加职位
	public void saveJob(TSysjob job);

	// 删除职位
	public void deleteJob(String jobid);

	// 修改职位
	public void updateJob(TSysjob job);

	// 修改初始化
	public TSysjob initUpdateJob(String jobid);

	// 职位记录
	public long count();
}
