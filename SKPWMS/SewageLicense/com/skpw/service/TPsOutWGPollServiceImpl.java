package com.skpw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TPsOutWGPoll;
import com.skpw.repository.TPsOutWGPollRepository;

@Service
public class TPsOutWGPollServiceImpl implements TPsOutWGPollService{

	@Autowired
	private TPsOutWGPollRepository tPsOutWGPollRepository;
	
	@Override
	public Page<TPsOutWGPoll> findAllByPage(TPsOutWGPoll tPsOutWGPoll,
			Pageable pager) {
		return tPsOutWGPollRepository.findAll(buildSpecification(tPsOutWGPoll), pager);
	}

	private Specification<TPsOutWGPoll> buildSpecification(final TPsOutWGPoll tPsOutWGPoll) {
		return new Specification<TPsOutWGPoll>() {

			@Override
			public Predicate toPredicate(Root<TPsOutWGPoll> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(tPsOutWGPoll != null && tPsOutWGPoll.gettPsWasteGasOutlet() != null && tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID() != null && !tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID().trim().equals("")) {
					predicates.add(cb.equal(root.get("tPsWasteGasOutlet").get("fWGOutletID"), tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID()));
				}
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
	public TPsOutWGPoll save(TPsOutWGPoll tPsOutWGPoll) {
		return tPsOutWGPollRepository.save(tPsOutWGPoll);
	}

	@Override
	public TPsOutWGPoll findOne(String id) {
		return tPsOutWGPollRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		tPsOutWGPollRepository.delete(id);
	}

	@Override
	public List<Map<String, String>> distinct(String fOutPID) {
		return tPsOutWGPollRepository.distinct(fOutPID);
	}
}
