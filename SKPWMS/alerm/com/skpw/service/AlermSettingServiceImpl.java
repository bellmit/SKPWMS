package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.AlermSetting;
import com.skpw.repository.AlermSettingRepository;

@Service("alermSettingService")
public class AlermSettingServiceImpl implements AlermSettingService {

	@Resource
	private AlermSettingRepository alermSettingRepository;

	public List showAlermSetting() {

		return alermSettingRepository.findAll();
	}

	public Page<AlermSetting> showAlermSettingByCondition(
			AlermSetting alermSetting, Pageable pageable,
			Specification<AlermSetting> spec) {

		return alermSettingRepository.findAll(spec, pageable);
	}

	public void saveAlermSetting(AlermSetting alermSetting) {

		alermSettingRepository.save(alermSetting);
	}

	public void delAlermSetting(String id) {
		alermSettingRepository.delete(id);

	}

	public long count() {

		return alermSettingRepository.count();
	}

}
