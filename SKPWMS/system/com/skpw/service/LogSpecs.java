package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysLog;
import com.skpw.bean.TSysjob;

public class LogSpecs {

	public static Specification<TSysLog> queryCondition(final TSysLog log,
			final String startTime, final String endTime) {
		return new Specification<TSysLog>() {

			final String st = startTime;
			final String et = endTime;

			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysLog> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (log.getId() != null && !"".equals(log.getId())) {
					Path<Integer> logid = root.get("id");
					predicates.add(bulider.equal(logid, log.getId()));
				}

				if (log.getOperatetype() != null
						&& !"".equals(log.getOperatetype())) {
					Path<Short> operatetype = root.get("operatetype");
					predicates.add(bulider.equal(operatetype,
							log.getOperatetype()));
				}

				if ((st!=null&&!"".equals(st))&& (et!=null&&!"".equals(et))) {
					
					Path<String> operatetime = root.get("operatetime");
					predicates.add(bulider.between(operatetime,startTime, endTime));
                   
				}

				return predicate;
			}
		};
	}

}
