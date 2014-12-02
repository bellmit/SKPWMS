package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasVillage;
import com.skpw.repository.TBasVillageRepository;

@Service("tBasVillageService")
public class TBasVillageServiceImpl  implements TBasVillageService{

	@Resource
	private TBasVillageRepository tBasVillageRepository;
	
	@Override
	public List<TBasVillage> getList(Specification<TBasVillage> spf) {
		// TODO Auto-generated method stub
		return tBasVillageRepository.findAll(spf);
	}

	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasVillageRepository.count();
	}

	@Override
	public List<TBasVillage> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasVillage> page = tBasVillageRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasVillage tem) {
		// TODO Auto-generated method stub
		tBasVillageRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasVillageRepository.delete(id);
	}

	@Override
	public TBasVillage findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}


}
