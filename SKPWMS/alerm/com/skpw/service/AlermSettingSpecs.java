package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.AlermSetting;

public class AlermSettingSpecs {

	public static Specification<AlermSetting> queryCondition(
			final AlermSetting alermSetting) {
		return new Specification<AlermSetting>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<AlermSetting> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (alermSetting.getParamid() != null
						&& !"".equals(alermSetting.getParamid())) {
					Path<Integer> paramid = root.get("paramid");
					predicates.add(bulider.equal(paramid,
							alermSetting.getParamid()));
				}

				if (alermSetting.getParamname() != null
						&& !"".equals(alermSetting.getParamname())) {
					Path<String> paramname = root.get("paramname");
					predicates.add(bulider.equal(paramname,
							alermSetting.getParamname()));
				}

				return predicate;
			}
		};
	}

}
