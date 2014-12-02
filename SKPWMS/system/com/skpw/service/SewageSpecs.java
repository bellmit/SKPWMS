package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasSewageLicence;

public class SewageSpecs {

	public static Specification<TBasSewageLicence> queryCondition(final TBasSewageLicence sewageLicence) {
		return new Specification<TBasSewageLicence>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TBasSewageLicence> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				 Predicate predicate = bulider.conjunction();
				 List<Expression<Boolean>> predicates = predicate.getExpressions();
				 if(sewageLicence.getId()!=null && !"".equals(sewageLicence.getId())){
						Path<Integer> xkzid= root.get("id");
						predicates.add(bulider.equal(xkzid, sewageLicence.getId()));
					}
					
					if (sewageLicence.getXkzbh()!=null&&!"".equals(sewageLicence.getXkzbh())) {
						Path<String> xkzbh= root.get("xkzbh");
						predicates.add(bulider.equal(xkzbh, sewageLicence.getXkzbh()));
					}
					
					
					return predicate;
			}
		};
	}
}
