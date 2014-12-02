package com.skpw.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.skpw.bean.TBasWaterDisStd;
import com.skpw.repository.TBasWaterDisStdRepository;

@Service("tBasWaterDisStdService")
public class TBasWaterDisStdServiceImpl implements TBasWaterDisStdService{

	@Resource
	private TBasWaterDisStdRepository tBasWaterDisStdRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasWaterDisStdRepository.count();
	}

	@Override
	public List<TBasWaterDisStd> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasWaterDisStd> page = tBasWaterDisStdRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasWaterDisStd tem) {
		// TODO Auto-generated method stub
		tBasWaterDisStdRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasWaterDisStdRepository.delete(id);
	}

	@Override
	public TBasWaterDisStd findOne(String id) {
		// TODO Auto-generated method stub
		return tBasWaterDisStdRepository.findOne(id);
	}

	@Override
	public List<TBasWaterDisStd> findAll() {
		// TODO Auto-generated method stub
		return tBasWaterDisStdRepository.findAll();
	}

}
