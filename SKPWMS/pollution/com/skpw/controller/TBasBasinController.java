package com.skpw.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.skpw.bean.TBasBasin;
import com.skpw.bean.TreeBean;
import com.skpw.service.TBasBasinService;

@Controller
public class TBasBasinController {

	@Resource
	private TBasBasinService tBasBasinService;
	
	/**
	 * 查询出所有流域信息，转换为ztree的简单数据格式
	 * @return
	 */
	@RequestMapping("/tBasBasinController/findAllForZtree")
	public @ResponseBody List<TreeBean> findAllForZtree(){
		List<TreeBean> list = new ArrayList<TreeBean>();
		List<TBasBasin> titlist = tBasBasinService.findAll();
		for(TBasBasin tbi:titlist){
			TreeBean tb = new TreeBean();
			tb.setId(tbi.getFbasinId().trim());
			if(tbi.getFparentId() == null){
				tb.setPid("0");
			}else{
				tb.setPid(tbi.getFparentId());
			}
			tb.setName(tbi.getFbasinName());
			list.add(tb);
		}
		return list;
	}
}
