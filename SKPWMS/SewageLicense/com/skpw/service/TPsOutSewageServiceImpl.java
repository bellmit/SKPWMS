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

import com.skpw.bean.TPsOutSewage;
import com.skpw.repository.TPsOutSewageRepository;

@Service
public class TPsOutSewageServiceImpl implements TPsOutSewageService{

	@Autowired
	private TPsOutSewageRepository tPsOutSewageRepository;
	
	@Override
	public Page<TPsOutSewage> findAllByPage(TPsOutSewage tPsOutSewage,Pageable pager) {
		return tPsOutSewageRepository.findAll(buildSpecification(tPsOutSewage),pager);
	}

	@Override
	public TPsOutSewage findOne(String id) {
		return tPsOutSewageRepository.findOne(id);
	}

	@Override
	public TPsOutSewage save(TPsOutSewage tPsOutSewage) {
		return tPsOutSewageRepository.save(tPsOutSewage);
	}

	@Override
	public void update(TPsOutSewage tPsOutSewage) {
//		TPsOutSewage _tPsOutSewage=tPsOutSewageRepository.findOne(tPsOutSewage.getId());
//		_tPsOutSewage.setName(tPsOutSewage.getName());
//		_tPsOutSewage.setClassify(tPsOutSewage.getClassify());
//		_tPsOutSewage.setCy(tPsOutSewage.getCy());
//		_tPsOutSewage.setFh(tPsOutSewage.getFh());
//		_tPsOutSewage.setJkwh(tPsOutSewage.getJkwh());
//		_tPsOutSewage.setLlyjcl(tPsOutSewage.getLlyjcl());
//		_tPsOutSewage.setWhxz(tPsOutSewage.getWhxz());
//		_tPsOutSewage.setWxtx(tPsOutSewage.getWxtx());
//		tPsOutSewageRepository.save(_tPsOutSewage);
	}

	@Override
	public void delete(String id) {
		tPsOutSewageRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tPsOutSewageRepository.delete(id);
		}
	}
	
	public TPsOutSewageRepository getTPsOutSewageRepository() {
		return tPsOutSewageRepository;
	}

	public void setTPsOutSewageRepository(TPsOutSewageRepository tPsOutSewageRepository) {
		this.tPsOutSewageRepository = tPsOutSewageRepository;
	}

	private Specification<TPsOutSewage> buildSpecification(final TPsOutSewage tPsOutSewage) {
		return new Specification<TPsOutSewage>() {

			@Override
			public Predicate toPredicate(Root<TPsOutSewage> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(null!=tPsOutSewage.gettPsOutPermit() && !"".equals(tPsOutSewage.gettPsOutPermit().getfOutPID())){
					Path<String> path=root.get("tPsOutPermit").get("fOutPID");
					predicates.add(cb.like(path, "%"+tPsOutSewage.gettPsOutPermit().getfOutPID()+"%"));
				}
				/*if(null!=tPsOutSewage.getName() && !"".equals(tPsOutSewage.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tPsOutSewage.getName()+"%"));
				}
				
				if(null!=tPsOutSewage.getClassify() && !"".equals(tPsOutSewage.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tPsOutSewage.getClassify()+"%"));
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
