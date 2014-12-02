package com.skpw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	@RequestMapping("test/jumpToChart")
	public String jumpToChart() {

		return "test/chart_list";
	}

}
