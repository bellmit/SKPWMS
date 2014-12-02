package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TSysjob;

public class JobSpecs {
	
	public static Specification<TSysjob> queryCondition(final TSysjob job) {
		return new Specification<TSysjob>() {
			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TSysjob> root,
					CriteriaQuery<?> query, CriteriaBuilder bulider) {
				Predicate predicate = bulider.conjunction();
				List<Expression<Boolean>> predicates = predicate
						.getExpressions();
				if (job.getId() != null && !"".equals(job.getId())) {
					Path<Integer> jobid = root.get("id");
					predicates.add(bulider.equal(jobid, job.getId()));
				}

				if (job.getJobname() != null
						&& !"".equals(job.getJobname())) {
					Path<String> jobname = root.get("jobname");
					predicates.add(bulider.equal(jobname,job.getJobname()));
				}

				return predicate;
			}
		};
	}

}
