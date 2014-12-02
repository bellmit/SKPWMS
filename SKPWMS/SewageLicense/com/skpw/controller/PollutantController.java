package com.skpw.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skpw.bean.TBasPollutant;
import com.skpw.bean.TPsOutSPoll;
import com.skpw.bean.TPsOutWGPoll;
import com.skpw.service.TBasPollutantService;

@Controller
public class PollutantController {
	
	@Autowired
	private TBasPollutantService tBasPollutantService;
		
	@RequestMapping("pollutant/list")
	public @ResponseBody List<TBasPollutant> list(TBasPollutant tBasPollutant){
		return tBasPollutantService.findAll(tBasPollutant);
	}
	
	@RequestMapping("pollutant/findByOutSewage")
	public @ResponseBody List<Map<String, String>> list(TPsOutSPoll tPsOutSPoll){
		return tBasPollutantService.findAllByOutSPoll(tPsOutSPoll);
	}
	
	@RequestMapping("pollutant/findBytPsWasteGasOutlet")
	public @ResponseBody List<Map<String, String>> listWasteGasOutlet(TPsOutWGPoll tPsOutWGPoll) {
		return tBasPollutantService.findAllByTPsOutWGPoll(tPsOutWGPoll);
	}
}
