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

import com.skpw.bean.TIcRecharge;
import com.skpw.repository.TIcRechargeRepository;

@Service
public class TIcRechargeServiceImpl implements TIcRechargeService{

	@Autowired
	private TIcRechargeRepository tIcRechargeRepository;
	
	@Override
	public Page<TIcRecharge> findAllByPage(TIcRecharge tIcRecharge,Pageable pager) {
		return tIcRechargeRepository.findAll(buildSpecification(tIcRecharge),pager);
	}

	@Override
	public TIcRecharge findOne(String id) {
		return tIcRechargeRepository.findOne(id);
	}

	@Override
	public TIcRecharge save(TIcRecharge tIcRecharge) {
		return tIcRechargeRepository.save(tIcRecharge);
	}

	@Override
	public void update(TIcRecharge tIcRecharge) {
//		TIcRecharge _tIcRecharge=tIcRechargeRepository.findOne(tIcRecharge.getId());
//		_tIcRecharge.setName(tIcRecharge.getName());
//		_tIcRecharge.setClassify(tIcRecharge.getClassify());
//		_tIcRecharge.setCy(tIcRecharge.getCy());
//		_tIcRecharge.setFh(tIcRecharge.getFh());
//		_tIcRecharge.setJkwh(tIcRecharge.getJkwh());
//		_tIcRecharge.setLlyjcl(tIcRecharge.getLlyjcl());
//		_tIcRecharge.setWhxz(tIcRecharge.getWhxz());
//		_tIcRecharge.setWxtx(tIcRecharge.getWxtx());
//		tIcRechargeRepository.save(_tIcRecharge);
	}

	@Override
	public void delete(String id) {
		tIcRechargeRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tIcRechargeRepository.delete(id);
		}
	}
	
	public TIcRechargeRepository getTIcRechargeRepository() {
		return tIcRechargeRepository;
	}

	public void setTIcRechargeRepository(TIcRechargeRepository tIcRechargeRepository) {
		this.tIcRechargeRepository = tIcRechargeRepository;
	}

	private Specification<TIcRecharge> buildSpecification(final TIcRecharge tIcRecharge) {
		return new Specification<TIcRecharge>() {

			@Override
			public Predicate toPredicate(Root<TIcRecharge> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				/*if(null!=tIcRecharge.getName() && !"".equals(tIcRecharge.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tIcRecharge.getName()+"%"));
				}
				
				if(null!=tIcRecharge.getClassify() && !"".equals(tIcRecharge.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tIcRecharge.getClassify()+"%"));
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

	@Override
	public TIcRecharge findOneByFRechargeNo(String fRechargeNo) {
		return tIcRechargeRepository.findByFRechargeNo(fRechargeNo);
	}
}
