package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.repository.WarningObjectRepository;

@Service("warningObjectService")
public class WarningObjectServiceImpl implements WarningObjectService {

	@Resource
	private WarningObjectRepository warningObjectRepository;

	public List showWarningObject() {

		return warningObjectRepository.findAll();

	}

}
