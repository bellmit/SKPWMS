package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasTown;

public class TBasTownSpecs {

	public static Specification<TBasTown> getOneForSearch(final TBasTown tbe) {
		return new Specification<TBasTown>() {

			public Predicate toPredicate(Root<TBasTown> root, CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();

				List<Expression<Boolean>> predicates = predicate.getExpressions();

				if (tbe.getFisDisable() !=null && !"".equals(tbe.getFisDisable())) {
					Path<String> fisDisable = root.get("fisDisable");
					predicates.add(bulider.equal(fisDisable, tbe.getFisDisable()));
				}
				
				return predicate;
			}
		};
	}
}
