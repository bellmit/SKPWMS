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

import com.skpw.bean.TIcRechargeDetails;
import com.skpw.repository.TIcRechargeDetailsRepository;

@Service
public class TIcRechargeDetailsServiceImpl implements TIcRechargeDetailsService{

	@Autowired
	private TIcRechargeDetailsRepository tIcRechargeDetailsRepository;
	
	@Override
	public Page<TIcRechargeDetails> findAllByPage(TIcRechargeDetails tIcRechargeDetails,Pageable pager) {
		return tIcRechargeDetailsRepository.findAll(buildSpecification(tIcRechargeDetails),pager);
	}

	@Override
	public TIcRechargeDetails findOne(String id) {
		return tIcRechargeDetailsRepository.findOne(id);
	}

	@Override
	public TIcRechargeDetails save(TIcRechargeDetails tIcRechargeDetails) {
		return tIcRechargeDetailsRepository.save(tIcRechargeDetails);
	}

	@Override
	public void update(TIcRechargeDetails tIcRechargeDetails) {
//		TIcRechargeDetails _tIcRechargeDetails=tIcRechargeDetailsRepository.findOne(tIcRechargeDetails.getId());
//		_tIcRechargeDetails.setName(tIcRechargeDetails.getName());
//		_tIcRechargeDetails.setClassify(tIcRechargeDetails.getClassify());
//		_tIcRechargeDetails.setCy(tIcRechargeDetails.getCy());
//		_tIcRechargeDetails.setFh(tIcRechargeDetails.getFh());
//		_tIcRechargeDetails.setJkwh(tIcRechargeDetails.getJkwh());
//		_tIcRechargeDetails.setLlyjcl(tIcRechargeDetails.getLlyjcl());
//		_tIcRechargeDetails.setWhxz(tIcRechargeDetails.getWhxz());
//		_tIcRechargeDetails.setWxtx(tIcRechargeDetails.getWxtx());
//		tIcRechargeDetailsRepository.save(_tIcRechargeDetails);
	}

	@Override
	public void delete(String id) {
		tIcRechargeDetailsRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tIcRechargeDetailsRepository.delete(id);
		}
	}
	
	public TIcRechargeDetailsRepository getTIcRechargeDetailsRepository() {
		return tIcRechargeDetailsRepository;
	}

	public void setTIcRechargeDetailsRepository(TIcRechargeDetailsRepository tIcRechargeDetailsRepository) {
		this.tIcRechargeDetailsRepository = tIcRechargeDetailsRepository;
	}

	private Specification<TIcRechargeDetails> buildSpecification(final TIcRechargeDetails tIcRechargeDetails) {
		return new Specification<TIcRechargeDetails>() {

			@Override
			public Predicate toPredicate(Root<TIcRechargeDetails> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				/*if(null!=tIcRechargeDetails.getName() && !"".equals(tIcRechargeDetails.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tIcRechargeDetails.getName()+"%"));
				}
				
				if(null!=tIcRechargeDetails.getClassify() && !"".equals(tIcRechargeDetails.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tIcRechargeDetails.getClassify()+"%"));
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
