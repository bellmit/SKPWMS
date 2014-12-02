package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.EwsWarningObject;
import com.skpw.bean.WarningObject;
import com.skpw.repository.EwsWarningObjectRepository;

@Service("ewsWarningObjectService")
public class EwsWarningObjectServiceImpl implements EwsWarningObjectService {

	@Resource
	private EwsWarningObjectRepository ewsWarningObjectRepository;

	public List showEwsWarningObjectModel() {

		return ewsWarningObjectRepository.findAll();
	}

	public Page<EwsWarningObject> showEwsWarningObjectModelByPage(
			Pageable pageable) {

		return ewsWarningObjectRepository.findAll(pageable);
	}

	public void saveEwsWarningObjectModel(EwsWarningObject ewsWarningObject) {

		ewsWarningObjectRepository.save(ewsWarningObject);
	}

	public void delEwsWarningObjectModel(String id) {

		ewsWarningObjectRepository.delete(id);
	}

	public long count() {

		return ewsWarningObjectRepository.count();
	}

	public List showEwsWarningObjectByWarnid(String warnid) {
		// List list = ewsWarningObjectRepository
		// .showEwsWarningObjectByWarnid(warnid);
		// List<EwsWarningObject> ewsWarningObjectList = new ArrayList();
		// for (int i = 0; i < list.size(); i++) {
		// EwsWarningObject ewsWarningObject = new EwsWarningObject();
		// Object[] objs = (Object[]) list.get(i);
		// ewsWarningObject.setId((String) objs[0]);
		// // ewsWarningObject.setWarningObject((WarningObject) objs[2]);
		// ewsWarningObjectList.add(ewsWarningObject);
		// }
		// return ewsWarningObjectList;

		return ewsWarningObjectRepository.showEwsWarningObjectByWarnid(warnid);
	}

}
