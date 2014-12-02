package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TPsPermits;
import com.skpw.repository.TPsPermitsRepository;

@Service
public class TPsPermitsServiceImpl implements TPsPermitsService{

	@Autowired
	private TPsPermitsRepository tPsPermitsRepository;
	
	@Override
	public Page<TPsPermits> findAllByPage(TPsPermits tPsPermits,Pageable pager) {
		return tPsPermitsRepository.findAll(buildSpecification(tPsPermits),pager);
	}

	@Override
	public TPsPermits findOne(String id) {
		return tPsPermitsRepository.findOne(id);
	}

	@Override
	public TPsPermits findByDetails(String fenterId, String fPollutantID,
			Long fYearID) {
		return tPsPermitsRepository.findByDetails(fenterId, fPollutantID, fYearID);
	}
	
	@Override
	public TPsPermits save(TPsPermits tPsPermits) {
		return tPsPermitsRepository.save(tPsPermits);
	}

	@Override
	public void update(TPsPermits tPsPermits) {
//		TPsPermits _tPsPermits=tPsPermitsRepository.findOne(tPsPermits.getId());
//		_tPsPermits.setName(tPsPermits.getName());
//		_tPsPermits.setClassify(tPsPermits.getClassify());
//		_tPsPermits.setCy(tPsPermits.getCy());
//		_tPsPermits.setFh(tPsPermits.getFh());
//		_tPsPermits.setJkwh(tPsPermits.getJkwh());
//		_tPsPermits.setLlyjcl(tPsPermits.getLlyjcl());
//		_tPsPermits.setWhxz(tPsPermits.getWhxz());
//		_tPsPermits.setWxtx(tPsPermits.getWxtx());
//		tPsPermitsRepository.save(_tPsPermits);
	}

	@Override
	public void delete(String id) {
		tPsPermitsRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tPsPermitsRepository.delete(id);
		}
	}
	
	public TPsPermitsRepository getTPsPermitsRepository() {
		return tPsPermitsRepository;
	}

	public void setTPsPermitsRepository(TPsPermitsRepository tPsPermitsRepository) {
		this.tPsPermitsRepository = tPsPermitsRepository;
	}

	private Specification<TPsPermits> buildSpecification(final TPsPermits tPsPermits) {
		return new Specification<TPsPermits>() {

			@Override
			public Predicate toPredicate(Root<TPsPermits> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				/*if(null!=tPsPermits.getName() && !"".equals(tPsPermits.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tPsPermits.getName()+"%"));
				}
				
				if(null!=tPsPermits.getClassify() && !"".equals(tPsPermits.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tPsPermits.getClassify()+"%"));
				}*/
				
				if(predicates.size()>0){
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				}else{
					query.where(cb.conjunction());
				}
				
				return query.getRestriction();
			}
			
		};
	}

	
}
