package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TTcControler;

public interface TTcControlerService {

	// 获取总量控制的编号和名称
	public List<Map<String, Object>> getMapList(String id);

	// 获取某企业的总量控制器列表 tree
	public List<Map<String, Object>> findTTcControlersOfWry(String id);

	// 根据id获取总量控制器
	public TTcControler findtccontrollerById(String id);
	
	//根据fid获取总量控制器
	public TTcControler findcrtlByFid(int fid);

	// 获取总量控制器所有信息
	public List showControllerInfo();

	// 分页显示总量控制器信息
	public Page<TTcControler> showControllerByPage(Pageable pager);

	// 统计条数
	public long count();

	// 保存总量控制器信息
	public void save(TTcControler ttc);

	// 根据企业id查询总量控制器信息
	public List<TTcControler> findByFenterId(String id);

	// 获取总量控制器名称
	public String getTTController(String id);

	public List<TTcControler> findByFenterId1(String id);

	public void deleteByEnterId(String fenterId);
}
