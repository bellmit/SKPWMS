package com.skpw.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.skpw.bean.TPsWasteGasOutlet;

public interface TPsWasteGasOutletService {

	public Page<TPsWasteGasOutlet> findAllByPage(TPsWasteGasOutlet tPsWasteGasOutlet, Pageable pager);
	
	public TPsWasteGasOutlet save(TPsWasteGasOutlet tPsWasteGasOutlet);
	
	public void delete(String id);
	
	public TPsWasteGasOutlet findOne(String id);
}
