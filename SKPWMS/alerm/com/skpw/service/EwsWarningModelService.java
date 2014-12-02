package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.EwsWarningModel;

public interface EwsWarningModelService {

	// 显示所有
	public List showEwsWarningModel();

	// 分页显示
	public Page<EwsWarningModel> showEwsWarningModelByPage(Pageable pageable);

	// 修改和保存
	public void saveEwsWarningModel(EwsWarningModel ewsWarningModel);

	// 删除
	public void delEwsWarningModel(String id);
	
	// 统计条数
	public long  count();
	
	//通过报警id查询
	public List showEwsWarningModelByWarnid(String warnid);

}
