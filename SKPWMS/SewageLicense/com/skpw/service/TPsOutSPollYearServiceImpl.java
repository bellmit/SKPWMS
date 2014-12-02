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
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutSPollYear;
import com.skpw.bean.TPsPermits;
import com.skpw.repository.TPsOutSPollYearRepository;
import com.skpw.repository.TPsPermitsRepository;

@Service
@Transactional
public class TPsOutSPollYearServiceImpl implements TPsOutSPollYearService{

	@Autowired
	private TPsOutSPollYearRepository tPsOutSPollYearRepository;
	
	@Autowired
	private TPsPermitsRepository tPsPermitsRepository;
	
	@Override
	public Page<TPsOutSPollYear> findAllByPage(TPsOutSPollYear tPsOutSPollYear,Pageable pager) {
		return tPsOutSPollYearRepository.findAll(buildSpecification(tPsOutSPollYear),pager);
	}

	@Override
	public TPsOutSPollYear findOne(String id) {
		return tPsOutSPollYearRepository.findOne(id);
	}

	@Override
	public TPsOutSPollYear save(TPsOutSPollYear tPsOutSPollYear, TPsOutPermit tPsOutPermit) {
		//如果有记录则修改，没有这新增，删除不关联
		TPsPermits tPsPermits =tPsPermitsRepository.findByDetails(tPsOutPermit.gettBasEnterprise().getFenterId(), 
				tPsOutSPollYear.gettBasPollutant().getfPollutantID(), tPsOutSPollYear.getfYearID());
		if (null == tPsPermits) {
			tPsPermits = new TPsPermits();
			tPsPermits.settBasEnterprise(tPsOutPermit.gettBasEnterprise());
			tPsPermits.settBasPollutant(tPsOutSPollYear.gettBasPollutant());
			tPsPermits.setfYearID(tPsOutSPollYear.getfYearID());
			tPsPermits.setfInitPermitValue(tPsOutSPollYear.getfDischarge());
			tPsPermits.setfAddPermit(0.0);
			tPsPermits.setfPermitValue(tPsOutSPollYear.getfDischarge());
		} else {
			tPsPermits.setfInitPermitValue(tPsOutSPollYear.getfDischarge());
		}
		tPsPermitsRepository.save(tPsPermits);
		return tPsOutSPollYearRepository.save(tPsOutSPollYear);
	}

	@Override
	public TPsOutSPollYear update(TPsOutSPollYear tPsOutSPollYear) {
		TPsPermits tPsPermits = new TPsPermits();
		tPsPermits.settBasPollutant(tPsOutSPollYear.gettBasPollutant());
		tPsPermits.setfYearID(tPsOutSPollYear.getfYearID());
		tPsPermits.setfInitPermitValue(tPsOutSPollYear.getfDischarge());
		return tPsOutSPollYearRepository.save(tPsOutSPollYear);
	}

	@Override
	public void delete(String id) {
		tPsOutSPollYearRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tPsOutSPollYearRepository.delete(id);
		}
	}
	
	public TPsOutSPollYearRepository getTPsOutSPollYearRepository() {
		return tPsOutSPollYearRepository;
	}

	public void setTPsOutSPollYearRepository(TPsOutSPollYearRepository tPsOutSPollYearRepository) {
		this.tPsOutSPollYearRepository = tPsOutSPollYearRepository;
	}

	private Specification<TPsOutSPollYear> buildSpecification(final TPsOutSPollYear tPsOutSPollYear) {
		return new Specification<TPsOutSPollYear>() {

			@Override
			public Predicate toPredicate(Root<TPsOutSPollYear> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(null!=tPsOutSPollYear.gettPsOutSewage() && !"".equals(tPsOutSPollYear.gettPsOutSewage().getfOutSewageID())){
					Path<String> path=root.get("tPsOutSewage").get("fOutSewageID");
					predicates.add(cb.like(path, "%"+tPsOutSPollYear.gettPsOutSewage().getfOutSewageID()+"%"));
				}
				/*if(null!=tPsOutSPollYear.getName() && !"".equals(tPsOutSPollYear.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tPsOutSPollYear.getName()+"%"));
				}
				
				if(null!=tPsOutSPollYear.getClassify() && !"".equals(tPsOutSPollYear.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tPsOutSPollYear.getClassify()+"%"));
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
