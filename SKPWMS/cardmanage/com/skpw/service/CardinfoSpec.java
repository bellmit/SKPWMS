package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.CardInfo;

public class CardinfoSpec {

	public static Specification<CardInfo> queryCondition(final CardInfo cardInfo) {
		return new Specification<CardInfo>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<CardInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (cardInfo.getEnterprise() != null) {
					if (cardInfo.getEnterprise().getFenterName() != null
							&& !"".equals(cardInfo.getEnterprise()
									.getFenterName())) {
						Path<String> path = root.get("enterprise").get(
								"fenterName");
						predicates.add(bulider.equal(path, cardInfo
								.getEnterprise().getFenterName()));
					}
				}

				return predicate;
			}
		};
	}

}
