package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.WarningSetup;

public class WarningSetupSpecs {

	public static Specification<WarningSetup> queryCondition(
			final WarningSetup warningSetup) {
		return new Specification<WarningSetup>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<WarningSetup> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (warningSetup.getSetupno() != null
						&& !"".equals(warningSetup.getSetupno())) {
					Path<String> setupno = root.get("setupno");
					predicates.add(bulider.equal(setupno,
							warningSetup.getSetupno()));
				}
				//
				// if (warningSetup.getWarningItem().getId() != null
				// && !"".equals(warningSetup.getWarningItem().getId())) {
				// Path<String> warnitemid = root.get("warningItem.id");
				// predicates.add(bulider.equal(warnitemid,
				// warningSetup.getWarningItem().getId()));
				// }

				if (warningSetup.getWarningType() != null
						&& !"".equals(warningSetup.getWarningType())) {
					Path<String> warningType = root.get("warningType");
					predicates.add(bulider.equal(warningType,
							warningSetup.getWarningType()));
				}

				return predicate;
			}
		};
	}

}
