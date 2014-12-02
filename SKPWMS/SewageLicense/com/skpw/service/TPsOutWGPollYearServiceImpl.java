package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TPsOutPermit;
import com.skpw.bean.TPsOutWGPollYear;
import com.skpw.bean.TPsPermits;
import com.skpw.repository.TPsOutWGPollYearRepository;
import com.skpw.repository.TPsPermitsRepository;

@Service
public class TPsOutWGPollYearServiceImpl implements TPsOutWGPollYearService {

	@Autowired
	private TPsOutWGPollYearRepository tPsOutWGPollYearRepository;
	
	@Autowired
	private TPsPermitsRepository tPsPermitsRepository;
	@Override
	public Page<TPsOutWGPollYear> findAllByPage(
			TPsOutWGPollYear tPsOutWGPollYear, Pageable pager) {
		return tPsOutWGPollYearRepository.findAll(buildSpecification(tPsOutWGPollYear), pager);
	}

	private Specification<TPsOutWGPollYear> buildSpecification(final TPsOutWGPollYear tPsOutWGPollYear) {
		return new Specification<TPsOutWGPollYear>() {

			@Override
			public Predicate toPredicate(Root<TPsOutWGPollYear> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(null != tPsOutWGPollYear.gettPsWasteGasOutlet() && !"".equals(tPsOutWGPollYear.gettPsWasteGasOutlet().getfWGOutletID())) {
					predicates.add(cb.equal(root.get("tPsWasteGasOutlet").get("fWGOutletID"), tPsOutWGPollYear.gettPsWasteGasOutlet().getfWGOutletID()));
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
	public TPsOutWGPollYear save(TPsOutWGPollYear tPsOutWGPollYear,
			TPsOutPermit tPsOutPermit) {
		TPsPermits tPsPermits = tPsPermitsRepository.findByDetails(tPsOutPermit.gettBasEnterprise().getFenterId(), tPsOutWGPollYear.gettBasPollutant().getfPollutantID(), tPsOutWGPollYear.getfYearID());
		if(null == tPsPermits) {
			tPsPermits = new TPsPermits();
			tPsPermits.settBasEnterprise(tPsOutPermit.gettBasEnterprise());
			tPsPermits.settBasPollutant(tPsOutWGPollYear.gettBasPollutant());
			tPsPermits.setfYearID(tPsOutWGPollYear.getfYearID());
			tPsPermits.setfInitPermitValue(tPsOutWGPollYear.getfDischarge());
			tPsPermits.setfAddPermit(0.0);
			tPsPermits.setfPermitValue(tPsOutWGPollYear.getfDischarge());
		} else {
			tPsPermits.setfInitPermitValue(tPsOutWGPollYear.getfDischarge());
		}
		tPsPermitsRepository.save(tPsPermits);
		return tPsOutWGPollYearRepository.save(tPsOutWGPollYear);
	}

	@Override
	public TPsOutWGPollYear findOne(String id) {
		return tPsOutWGPollYearRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		tPsOutWGPollYearRepository.delete(id);
	}
}
