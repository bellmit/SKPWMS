package com.skpw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TSysjob;
import com.skpw.common.Page;
import com.skpw.service.JobService;
import com.skpw.service.JobSpecs;

@Controller
public class JobController {
	@Resource
	private JobService jobService;

	// 跳转到职位页面
	@RequestMapping("/job/initJobToList")
	public String initJobToList() {

		return "sys/job_list";
	}

	// 显示所有
	@RequestMapping("/job/showJob")
	@ResponseBody
	public List showJob(Model model) {
		List<TSysjob> jobList = jobService.showJobInfo();

		return jobList;
	}

	// 分页条件显示所有
	@RequestMapping("/job/showJobbyCondition")
	@ResponseBody
	public Map showJobbyCondition(Model model, TSysjob job,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "rows", required = false) Integer numPerPage) {
		Map<String, Object> map=new HashMap<String, Object>();
		if (pageNum == null || pageNum <= 0) {
			pageNum = new Integer(1);
		}
		if (numPerPage == null || numPerPage <= 0) {
			numPerPage = new Integer(10);
		}
		long count = jobService.count();
		Page page = new Page(pageNum, numPerPage, count);
		Pageable pageRequest = new PageRequest(page.getCurrentPage() - 1,
				page.getPageSize());
		List<TSysjob> jobList = jobService.showJobListByCondition(
				JobSpecs.queryCondition(job), pageRequest).getContent();
		
		map.put("total", count);
		map.put("rows", jobList);
		return map;
	}

	// 添加初始化
	@RequestMapping("/job/initAddJob")
	public String initAddJob(Model model) {

		return "job/sewagemange_add";
	}

	// 添加

	@RequestMapping("/job/addJob")
	@ResponseBody
	public String addJob(Model model, TSysjob job) {
		String result = "";
		try {
			jobService.saveJob(job);

			result = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 删除
	@RequestMapping("/job/delJob")
	@ResponseBody
	public Map<String, Boolean> delJob(Model model, String id) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		boolean result = false;
		try {

			jobService.deleteJob(id);
			result = true;

		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		map.put("success", result);

		return map;
	}

}
