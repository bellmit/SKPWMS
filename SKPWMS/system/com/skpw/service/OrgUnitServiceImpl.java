package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TSysorgUnit;
import com.skpw.repository.OrgUnitRepository;

@Service("orgUnitService")
public class OrgUnitServiceImpl implements OrgUnitService {

	@Resource
	private OrgUnitRepository orgUnitRepository;

	public List<TSysorgUnit> showTSysorgUnitInfo() {

		return orgUnitRepository.findAll();
	}

	public Page<TSysorgUnit> showTSysorgUnitListByCondition(
			Specification<TSysorgUnit> spec, Pageable pager) {

		return orgUnitRepository.findAll(spec, pager);
	}

	public void saveTSysorgUnit(TSysorgUnit orgUnit) {

		orgUnitRepository.save(orgUnit);
	}

	public void deleteTSysorgUnit(String orgid) {

		orgUnitRepository.delete(orgid);
	}

	public void updateTSysorgUnit(TSysorgUnit orgUnit) {

		orgUnitRepository.save(orgUnit);
	}

	public TSysorgUnit initUpdateTSysorgUnit(String orgid) {

		return orgUnitRepository.findOne(orgid);
	}

	public long count() {

		return orgUnitRepository.count();
	}

	public List<TSysorgUnit> findNodesByParentId(String id)
	{
		return orgUnitRepository.findNodesByParentId(id);
	}

	@Override
	public List<TSysorgUnit> findByOrgUnitCode(String orgUnitCode) {
		return orgUnitRepository.findByOrgUnitCode(orgUnitCode);
	}

	@Override
	public TSysorgUnit findOne(String id) {
		return orgUnitRepository.findOne(id);
	}

	@Override
	public List<TSysorgUnit> findByLongCode(String longCode) {
		return orgUnitRepository.findByLongCode(longCode);
	}

}
