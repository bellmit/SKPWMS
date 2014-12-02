package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.EwsWarningObject;

public interface EwsWarningObjectService {
	// 显示所有
	public List showEwsWarningObjectModel();

	// 分页显示
	public Page<EwsWarningObject> showEwsWarningObjectModelByPage(Pageable pageable);

	// 修改和保存
	public void saveEwsWarningObjectModel(EwsWarningObject ewsWarningObject);

	// 删除
	public void delEwsWarningObjectModel(String id);
	
	
	// 统计条数
	public long  count();
	
	//通过报警id查询
		public List showEwsWarningObjectByWarnid(String warnid);
}
