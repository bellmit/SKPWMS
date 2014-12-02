package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasCity;

public class TBasCitySpecs {

		public static Specification<TBasCity> getCities(final TBasCity tbe) {
			return new Specification<TBasCity>() {

				public Predicate toPredicate(Root<TBasCity> root, CriteriaQuery<?> query, CriteriaBuilder bulider) {

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
