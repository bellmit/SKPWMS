package com.skpw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.skpw.bean.TPsOutSPoll;
import com.skpw.repository.TPsOutSPollRepository;

@Service
public class TPsOutSPollServiceImpl implements TPsOutSPollService{

	@Autowired
	private TPsOutSPollRepository tPsOutSPollRepository;
	
	@Override
	public Page<TPsOutSPoll> findAllByPage(TPsOutSPoll tPsOutSPoll,Pageable pager) {
		return tPsOutSPollRepository.findAll(buildSpecification(tPsOutSPoll),pager);
	}

	@Override
	public TPsOutSPoll findOne(String id) {
		return tPsOutSPollRepository.findOne(id);
	}

	@Override
	public TPsOutSPoll save(TPsOutSPoll tPsOutSPoll) {
		return tPsOutSPollRepository.save(tPsOutSPoll);
	}

	@Override
	public void update(TPsOutSPoll tPsOutSPoll) {
//		TPsOutSPoll _tPsOutSPoll=tPsOutSPollRepository.findOne(tPsOutSPoll.getId());
//		_tPsOutSPoll.setName(tPsOutSPoll.getName());
//		_tPsOutSPoll.setClassify(tPsOutSPoll.getClassify());
//		_tPsOutSPoll.setCy(tPsOutSPoll.getCy());
//		_tPsOutSPoll.setFh(tPsOutSPoll.getFh());
//		_tPsOutSPoll.setJkwh(tPsOutSPoll.getJkwh());
//		_tPsOutSPoll.setLlyjcl(tPsOutSPoll.getLlyjcl());
//		_tPsOutSPoll.setWhxz(tPsOutSPoll.getWhxz());
//		_tPsOutSPoll.setWxtx(tPsOutSPoll.getWxtx());
//		tPsOutSPollRepository.save(_tPsOutSPoll);
	}

	@Override
	public void delete(String id) {
		tPsOutSPollRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tPsOutSPollRepository.delete(id);
		}
	}
	
	public TPsOutSPollRepository getTPsOutSPollRepository() {
		return tPsOutSPollRepository;
	}

	public void setTPsOutSPollRepository(TPsOutSPollRepository tPsOutSPollRepository) {
		this.tPsOutSPollRepository = tPsOutSPollRepository;
	}

	private Specification<TPsOutSPoll> buildSpecification(final TPsOutSPoll tPsOutSPoll) {
		return new Specification<TPsOutSPoll>() {

			@Override
			public Predicate toPredicate(Root<TPsOutSPoll> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(null!=tPsOutSPoll.gettPsOutSewage() && !"".equals(tPsOutSPoll.gettPsOutSewage().getfOutSewageID())){
					Path<String> path=root.get("tPsOutSewage").get("fOutSewageID");
					predicates.add(cb.like(path, "%"+tPsOutSPoll.gettPsOutSewage().getfOutSewageID()+"%"));
				}
				/*if(null!=tPsOutSPoll.getName() && !"".equals(tPsOutSPoll.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tPsOutSPoll.getName()+"%"));
				}
				
				if(null!=tPsOutSPoll.getClassify() && !"".equals(tPsOutSPoll.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tPsOutSPoll.getClassify()+"%"));
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
	public List<Map<String, String>> distinct(String fOutPID) {
		return tPsOutSPollRepository.distinct(fOutPID);
	}
}
