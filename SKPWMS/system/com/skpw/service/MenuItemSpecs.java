package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysMenu;

public class MenuItemSpecs {

	public static Specification<TSysMenu> queryCondition(final TSysMenu menu) {
		return new Specification<TSysMenu>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysMenu> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (menu.getId() != null && !"".equals(menu.getId())) {
					Path<Integer> menuid = root.get("id");
					predicates.add(bulider.equal(menuid, menu.getId()));
				}

				if (menu.getMenuName() != null
						&& !"".equals(menu.getMenuName())) {
					Path<String> menuName = root.get("menuName");
					predicates.add(bulider.equal(menuName, menu.getMenuName()));
				}

				return predicate;
			}
		};
	}

}
