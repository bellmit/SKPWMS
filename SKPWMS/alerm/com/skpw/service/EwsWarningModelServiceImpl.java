package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skpw.bean.EwsWarningModel;
import com.skpw.repository.EwsWarningModelRepository;

@Service("ewsWarningModelService")
public class EwsWarningModelServiceImpl implements EwsWarningModelService {

	@Resource
	private EwsWarningModelRepository ewsWarningModelRepository;

	public List showEwsWarningModel() {

		return ewsWarningModelRepository.findAll();
	}

	public Page<EwsWarningModel> showEwsWarningModelByPage(Pageable pageable) {

		return ewsWarningModelRepository.findAll(pageable);
	}

	public void saveEwsWarningModel(EwsWarningModel ewsWarningModel) {
		ewsWarningModelRepository.save(ewsWarningModel);

	}

	public void delEwsWarningModel(String id) {

		ewsWarningModelRepository.delete(id);
	}

	public long count() {

		return ewsWarningModelRepository.count();
	}

	public List showEwsWarningModelByWarnid(String warnid) {

		// List list = ewsWarningModelRepository
		// .showEwsWarningModelByWarnid(warnid);
		// List<EwsWarningModel> ewsWarningModelList = new ArrayList();
		// for (int i = 0; i < list.size(); i++) {
		// EwsWarningModel ewsWarningModel = new EwsWarningModel();
		// Object[] objs = (Object[]) list.get(i);
		// ewsWarningModel.setId((String) objs[0]);
		// // ewsWarningModel.setWarningModel((String) objs[2]);
		// ewsWarningModelList.add(ewsWarningModel);
		// }

		// return ewsWarningModelList;

		return ewsWarningModelRepository.showEwsWarningModelByWarnid(warnid);
	}

}
