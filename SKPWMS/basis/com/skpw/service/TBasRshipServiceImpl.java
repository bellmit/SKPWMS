package com.skpw.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.skpw.bean.TBasRship;
import com.skpw.repository.TBasRshipRepository;

@Service("tBasRshipService")
public class TBasRshipServiceImpl implements TBasRshipService{

	@Resource
	private TBasRshipRepository tBasRshipRepository;
	
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return (int) tBasRshipRepository.count();
	}

	@Override
	public List<TBasRship> findByPage(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<TBasRship> page = tBasRshipRepository.findAll(pageable);
		return page.getContent();
	}

	@Override
	public void save(TBasRship tem) {
		// TODO Auto-generated method stub
		tBasRshipRepository.save(tem);
	}

	@Override
	public void del(String id) {
		// TODO Auto-generated method stub
		tBasRshipRepository.delete(id);
	}

	@Override
	public TBasRship findOne(String id) {
		// TODO Auto-generated method stub
		return tBasRshipRepository.findOne(id);
	}

	@Override
	public List<TBasRship> findAll() {
		// TODO Auto-generated method stub
		return tBasRshipRepository.findAll();
	}


}
