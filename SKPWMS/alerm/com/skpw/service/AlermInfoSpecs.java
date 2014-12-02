package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.Alerminfo;

public class AlermInfoSpecs {
	public static Specification<Alerminfo> queryCondition(
			final Alerminfo alerminfo, final String startTime,
			final String endTime) {
		return new Specification<Alerminfo>() {

			final String st = startTime;
			final String et = endTime;

			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<Alerminfo> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (alerminfo.getAlermparam() != null
						&& !"".equals(alerminfo.getAlermparam())) {
					Path<Integer> alermparam = root.get("alermparam");
					predicates.add(bulider.equal(alermparam,
							alerminfo.getAlermparam()));
				}

				if ((st != null&&!"".equals(st))&& (et != null&&!"".equals(et))) {

					Path<String> alermtime = root.get("alermtime");
					predicates.add(bulider.between(alermtime, startTime,
							endTime));

				}

				return predicate;
			}
		};
	}
}
