package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.repository.WarningItemRepository;

@Service("warningItemService")
public class WarningItemServiceImpl implements WarningItemService {

	@Resource
	private WarningItemRepository warningItemRepository;

	public List showWarningItem() {

		return warningItemRepository.findAll();
	}

}
