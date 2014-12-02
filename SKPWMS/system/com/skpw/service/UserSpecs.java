package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysUserInfo;

public class UserSpecs {

	public static Specification<TSysUserInfo> queryCondition(final TSysUserInfo userInfo) {
		return new Specification<TSysUserInfo>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysUserInfo> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				 Predicate predicate = bulider.conjunction();
				 List<Expression<Boolean>> predicates = predicate.getExpressions();
				 if(userInfo.getId()!=null && !"".equals(userInfo.getId())){
						Path<String> userid= root.get("id");
						predicates.add(bulider.like(userid, "%"+userInfo.getId()+"%"));
					}
					
					if (userInfo.getUsername()!=null&&!"".equals(userInfo.getUsername())) {
						Path<String> username= root.get("username");
						predicates.add(bulider.like(username, "%"+userInfo.getUsername()+"%"));
					}
					
					
					return predicate;
			}
		};
	}
}
