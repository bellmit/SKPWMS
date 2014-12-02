package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysRole;

public class RoleSpecs {

	public static Specification<TSysRole> queryCondition(final TSysRole role) {
		return new Specification<TSysRole>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysRole> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (role.getId() != null && !"".equals(role.getId())) {
					Path<Integer> roleid = root.get("id");
					predicates.add(bulider.equal(roleid, role.getId()));
				}

				if (role.getRolename() != null
						&& !"".equals(role.getRolename())) {
					Path<String> rolename = root.get("rolename");
					predicates.add(bulider.like(rolename, "%"+role.getRolename()+"%"));
				}

				return predicate;
			}
		};
	}

}
