package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasIndustryType;

public class TBasIndustryTypeSpecs {
	
	public static Specification<TBasIndustryType> getOneForSearch(final TBasIndustryType tbe) {
		return new Specification<TBasIndustryType>() {

			public Predicate toPredicate(Root<TBasIndustryType> root, CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();

				List<Expression<Boolean>> predicates = predicate.getExpressions();

				if (tbe.getFparentId() !=null && !"".equals(tbe.getFparentId())) {
					Path<String> fparentId = root.get("fparentId");
					predicates.add(bulider.equal(fparentId, tbe.getFisDisable()));
				}
				
				return predicate;
			}
		};
	}
}
