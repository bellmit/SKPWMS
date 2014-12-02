package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import com.skpw.bean.TTcControler;

public class ControlerSpecs {

	//总量控制器
		public static Specification<TTcControler> getContrlers(final TTcControler tbe) {
			return new Specification<TTcControler>() {

				@SuppressWarnings("unchecked")
				public Predicate toPredicate(Root<TTcControler> root, CriteriaQuery<?> query, CriteriaBuilder bulider) {

					Predicate predicate = bulider.conjunction();

					List<Expression<Boolean>> predicates = predicate.getExpressions();

					if (tbe.getFenterId() !=null && !"".equals(tbe.getFenterId())) {
						Path<String> fenterId = root.get("fenterId");
						predicates.add(bulider.equal(fenterId, tbe.getFenterId()));
					}

					return predicate;
				}
			};
		}
}
