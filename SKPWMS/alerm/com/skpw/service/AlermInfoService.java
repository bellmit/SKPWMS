package com.skpw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.Alerminfo;

public interface AlermInfoService {
	
	//显示所有
	public List showAlermInfo();
	
	//分页显示
	public Page<Alerminfo> showAlermInfoByCondition(Pageable pageable, Specification<Alerminfo> spec);
	
	//报警记录数
	public long count();

}
