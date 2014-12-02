package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.OfflineLog;

public class OfflineLogSpecs {

	public static Specification<OfflineLog> queryCondition(
			final OfflineLog offlineLog, final String startTime,
			final String endTime) {
		return new Specification<OfflineLog>() {
			final String st = startTime;
			final String et = endTime;

			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<OfflineLog> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (offlineLog.getEnterprise() != null) {
					if (offlineLog.getEnterprise().getFenterName() != null
							&& !"".equals(offlineLog.getEnterprise()
									.getFenterName())) {
						Path<String> path = root.get("enterprise").get(
								"fenterName");
						predicates.add(bulider.like(path, "%"
								+ offlineLog.getEnterprise().getFenterName()
								+ "%"));
					}
				}
				
				if (null != offlineLog.getEnterprise()) {
					if (null != offlineLog.getEnterprise().getFenterId()
							&& !"".equals(offlineLog.getEnterprise()
									.getFenterId())) {
						Path<String> path = root.get("enterprise").get(
								"fenterId");
						predicates.add(bulider.equal(path, offlineLog.getEnterprise().getFenterId()));
					}
				}
				
				if (null != offlineLog.getEnterprise()) {
					if (null != offlineLog.getEnterprise().getFlcList()
							&& offlineLog.getEnterprise().getFlcList().size()>0) {
						predicates.add(bulider.in(root.get("enterprise").get("forgUnitId")).value(offlineLog.getEnterprise().getFlcList()));
					}
				}

				if ((st != null && !"".equals(st))
						&& (et != null && !"".equals(et))) {

					Path<String> startdate = root.get("startdate");
					predicates.add(bulider.between(startdate, startTime,
							endTime));

				}
				return predicate;
			}
		};
	}

}
