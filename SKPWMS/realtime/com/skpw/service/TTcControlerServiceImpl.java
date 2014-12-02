package com.skpw.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.TTcControler;
import com.skpw.dao.TTcControlerDao;
import com.skpw.repository.TTCControllerRepository;

@Service("tTcControlerService")
public class TTcControlerServiceImpl implements TTcControlerService {

	@Resource
	private TTcControlerDao tTcControlerDao;

	@Resource
	private TTCControllerRepository ttcControllerRepository;

	public List<Map<String, Object>> getMapList(String id) {

		return tTcControlerDao.getMapList(id);
	}

	public List<Map<String, Object>> findTTcControlersOfWry(String id) {

		return tTcControlerDao.findTTcControlersOfWry(id);
	}

	public TTcControler findtccontrollerById(String id) {

		return ttcControllerRepository.findOne(id);
	}

	public List showControllerInfo() {

		return ttcControllerRepository.findAll();
	}

	public Page<TTcControler> showControllerByPage(Pageable pager) {

		return ttcControllerRepository.findAll(pager);
	}

	public long count() {

		return ttcControllerRepository.count();
	}

	
	public void save(TTcControler ttc) {
		// TODO Auto-generated method stub
		ttcControllerRepository.save(ttc);
	}

	
	public List<TTcControler> findByFenterId(String id) {
		return ttcControllerRepository.findByFenterId(id);
	}

	public String getTTController(String id) {
		String controllername = "";
		List<TTcControler> list = ttcControllerRepository.findByFenterId(id);
		for (TTcControler tTcControler : list) {

			controllername = tTcControler.getFctrlerName();
		}

		return controllername;
	}

	public List<TTcControler> findByFenterId1(String id) {
		return ttcControllerRepository.findByFenterId1(id);
	}

	
	public void deleteByEnterId(String fenterId) {
		this.ttcControllerRepository.deleteByEnterId(fenterId);
	}

	
	public TTcControler findcrtlByFid(int fid) {
		
		return ttcControllerRepository.findcrtlByFid(fid);
	}
}
