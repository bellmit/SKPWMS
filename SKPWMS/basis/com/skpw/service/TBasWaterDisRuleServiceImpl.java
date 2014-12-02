package com.skpw.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.skpw.bean.TBasWaterDisRule;
import com.skpw.repository.TBasWaterDisRuleRepository;

@Service("tBasWaterDisRuleService")
public class TBasWaterDisRuleServiceImpl implements TBasWaterDisRuleService{

	@Resource
	private TBasWaterDisRuleRepository tBasWaterDisRuleRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasWaterDisRuleRepository.count();
	}

	@Override
	public List<TBasWaterDisRule> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasWaterDisRule> page = tBasWaterDisRuleRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasWaterDisRule tem) {
		// TODO Auto-generated method stub
		tBasWaterDisRuleRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasWaterDisRuleRepository.delete(id);
	}

	@Override
	public TBasWaterDisRule findOne(String id) {
		// TODO Auto-generated method stub
		return tBasWaterDisRuleRepository.findOne(id);
	}

	@Override
	public List<TBasWaterDisRule> findAll() {
		// TODO Auto-generated method stub
		return tBasWaterDisRuleRepository.findAll();
	}

}
