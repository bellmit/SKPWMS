package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TBasEnterprise;
import com.skpw.repository.WryjbxxRepository;

@Service("wryjbxxService")
public class WryjbxxServiceImpl implements WryjbxxService{

	@Resource
	private WryjbxxRepository wryjbxxRepository;
	
	@Override
	public List<TBasEnterprise> findPollutionBasInfo(Specification<TBasEnterprise> spf, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasEnterprise> accounts = wryjbxxRepository.findAll(spf, pageable);
		return accounts.getContent();
	}

	@Override
	public int findPollutionBasCount() {
		// TODO Auto-generated method stub
		return (int) wryjbxxRepository.count();
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		wryjbxxRepository.delete(id);
	}

	@Override
	public void save(TBasEnterprise tbe) {
		// TODO Auto-generated method stub
		wryjbxxRepository.save(tbe);
	}

	@Override
	public TBasEnterprise findWry(String id) {
		// TODO Auto-generated method stub
		return wryjbxxRepository.findOne(id);
	}

	@Override
	public List<TBasEnterprise> findAll() {
		// TODO Auto-generated method stub
		return wryjbxxRepository.findAll();
	}

	@Override
	public List<TBasEnterprise> findPollutionBasInfoByCondition(
			Specification<TBasEnterprise> spf) {
		
		return wryjbxxRepository.findAll(spf);
	}

	@Override
	public Page<TBasEnterprise> findPollutionBasInfonew(
			Specification<TBasEnterprise> spf, Pageable pageable) {
		return wryjbxxRepository.findAll(spf, pageable);
	}

	@Override
	public List<String> findenterIdsByzzjgid(List<String> zzjgids) {
		return wryjbxxRepository.findenterIdsByzzjgid(zzjgids);
	}

	@Override
	public List<TBasEnterprise> findqylistByzzjgid(List<String> zzjgs) {
		return wryjbxxRepository.findqylistByzzjgid(zzjgs);
	}

}
