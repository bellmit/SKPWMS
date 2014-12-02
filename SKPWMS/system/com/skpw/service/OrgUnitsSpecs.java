package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysorgUnit;

public class OrgUnitsSpecs {

	public static Specification<TSysorgUnit> queryCondition(
			final TSysorgUnit orgUnit) {
		return new Specification<TSysorgUnit>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysorgUnit> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (orgUnit.getId() != null && !"".equals(orgUnit.getId())) {
					Path<String> userid = root.get("id");
					predicates.add(bulider.like(userid, "%" + orgUnit.getId()
							+ "%"));
				}

				if (orgUnit.getOrgUnitName() != null
						&& !"".equals(orgUnit.getOrgUnitName())) {
					Path<String> orgUnitName = root.get("orgUnitName");
					predicates.add(bulider.equal(orgUnitName,
							orgUnit.getOrgUnitName()));
				}

				return predicate;
			}
		};
	}

}
