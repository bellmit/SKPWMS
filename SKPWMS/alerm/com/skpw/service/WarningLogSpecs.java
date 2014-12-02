package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.WarningLog;

public class WarningLogSpecs {
	
	public static Specification<WarningLog> queryCondition(
			final WarningLog warningLog, final String startTime,
			final String endTime) {
		return new Specification<WarningLog>() {

			final String st = startTime;
			final String et = endTime;

			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<WarningLog> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if(null != warningLog.getEnterprise() && null != warningLog.getEnterprise().getFenterId() && !"".equals(warningLog.getEnterprise().getFenterId())) {
					predicates.add(bulider.equal(root.get("enterprise").get("fenterId"), warningLog.getEnterprise().getFenterId()));
				}
				
				if(null != warningLog.getEnterprise() && null != warningLog.getEnterprise().getFlcList() && warningLog.getEnterprise().getFlcList().size()>0) {
					predicates.add(bulider.in(root.get("enterprise").get("forgUnitId")).value(warningLog.getEnterprise().getFlcList()));
				}

				if ((st != null&&!"".equals(st))&& (et != null&&!"".equals(et))) {

					Path<String> warningTime = root.get("warningTime");
					predicates.add(bulider.between(warningTime, startTime,
							endTime));

				}

				return predicate;
			}
		};
	}

}
