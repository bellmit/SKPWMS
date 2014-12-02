package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.AlermSetting;

public interface AlermSettingService {

	// 显示
	public List showAlermSetting();

	// 条件查询
	public Page<AlermSetting> showAlermSettingByCondition(AlermSetting alermSetting,
			Pageable pageable, Specification<AlermSetting> spec);

	// 保存修改

	public void saveAlermSetting(AlermSetting alermSetting);

	// 删除

	public void delAlermSetting(String id);
	
	//记录数
	public long count();
}
