package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysAuthority;

public class AuthoritySpecs {
	
	public static Specification<TSysAuthority> queryCondition(final TSysAuthority authority) {
		return new Specification<TSysAuthority>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysAuthority> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				 Predicate predicate = bulider.conjunction();
				 List<Expression<Boolean>> predicates = predicate.getExpressions();
				 if(authority.getId()!=null && !"".equals(authority.getId())){
						Path<Integer> id= root.get("id");
						predicates.add(bulider.equal(id, authority.getId()));
					}
					
					if (authority.getAuthorityName()!=null&&!"".equals(authority.getAuthorityName())) {
						Path<String> authorityName= root.get("authorityName");
						predicates.add(bulider.like(authorityName, "%"+authority.getAuthorityName()+"%"));
					}
					
					
					return predicate;
			}
		};
	}

}
