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

import com.skpw.bean.TPsWasteGasOutlet;
import com.skpw.repository.TPsWasteGasOutletRepository;

@Service
public class TPsWasteGasOutletServiceImpl implements TPsWasteGasOutletService {

	@Autowired
	private TPsWasteGasOutletRepository tPsWasteGasOutletRepository;
	@Override
	public Page<TPsWasteGasOutlet> findAllByPage(TPsWasteGasOutlet tPsWasteGasOutlet, Pageable pager) {
		return tPsWasteGasOutletRepository.findAll(buildSpecification(tPsWasteGasOutlet), pager);
	}

	private Specification<TPsWasteGasOutlet> buildSpecification(final TPsWasteGasOutlet tPsWasteGasOutlet) {
		return new Specification<TPsWasteGasOutlet>() {

			@Override
			public Predicate toPredicate(Root<TPsWasteGasOutlet> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if(tPsWasteGasOutlet != null && tPsWasteGasOutlet.gettPsOutPermit() != null && tPsWasteGasOutlet.gettPsOutPermit().getfOutPID() != null && !tPsWasteGasOutlet.gettPsOutPermit().getfOutPID().trim().equals("")) {
					predicates.add(cb.equal(root.get("tPsOutPermit").get("fOutPID"), tPsWasteGasOutlet.gettPsOutPermit().getfOutPID()));
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
	public TPsWasteGasOutlet save(TPsWasteGasOutlet tPsWasteGasOutlet) {
		return tPsWasteGasOutletRepository.save(tPsWasteGasOutlet);
	}

	@Override
	public void delete(String id) {
		tPsWasteGasOutletRepository.delete(id);
	}

	@Override
	public TPsWasteGasOutlet findOne(String id) {
		return tPsWasteGasOutletRepository.findOne(id);
	}
}
