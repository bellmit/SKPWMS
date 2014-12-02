package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.SMAdjust;

public class SMAdjustSpecs {

	public static Specification<SMAdjust> queryCondition(
			final SMAdjust smAdjust) {
		return new Specification<SMAdjust>() {

			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<SMAdjust> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (smAdjust.getEnterprise() != null) {
					if (smAdjust.getEnterprise().getFenterName() != null
							&& !"".equals(smAdjust.getEnterprise()
									.getFenterName())) {
						Path<String> path = root.get("enterprise").get(
								"fenterName");
						predicates.add(bulider.like(path, "%"+smAdjust
								.getEnterprise().getFenterName()+"%"));
					}
					
					if (smAdjust.getEnterprise().getFenterId() != null
							&& !"".equals(smAdjust.getEnterprise()
									.getFenterId().trim())) {
						Path<String> path = root.get("enterprise").get(
								"fenterId");
						predicates.add(bulider.equal(path, smAdjust
								.getEnterprise().getFenterId()));
					}
					
				}
				return predicate;
			}
		};
	}

}
