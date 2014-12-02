package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.SMAdjust;

public interface SMAdjustService {

	// 显示所有月度调整数据
	public List<SMAdjust> showSMAdjustInfo();

	// 分页显示月度调整数据
	public Page<SMAdjust> showSMAdjustListByCondition(Specification<SMAdjust> spec,
			Pageable pager);

	// 添加月度调整数据
	public void saveSMAdjust(SMAdjust smadjust);

	// 删除调整数据
	public void deleteSMAdjust(String smadjustid);

	//月度数据记录
	public long count(Specification<SMAdjust> spec);
	
	public SMAdjust isExist(int pollutant, int monthid);

}
