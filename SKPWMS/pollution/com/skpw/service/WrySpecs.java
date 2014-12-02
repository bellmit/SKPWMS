package com.skpw.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.TBasEnterprise;

public class WrySpecs {
	
	//污染源基本信息
	public static Specification<TBasEnterprise> wryjbxx(final TBasEnterprise tbe) {
		return new Specification<TBasEnterprise>() {

			@SuppressWarnings("unchecked")
			public Predicate toPredicate(Root<TBasEnterprise> root, CriteriaQuery<?> query, CriteriaBuilder bulider) {

				Predicate predicate = bulider.conjunction();

				List<Expression<Boolean>> predicates = predicate.getExpressions();
				
				if (tbe.getFenterName()!=null && !"".equals(tbe.getFenterName())) {
					Path<String> fenterName = root.get("fenterName");
					predicates.add(bulider.equal(fenterName, tbe.getFenterName()));
				}

				if (tbe.getForgUnitId()!=null && !"".equals(tbe.getForgUnitId())) {
					Path<String> forgUnitId = root.get("forgUnitId");
					predicates.add(bulider.equal(forgUnitId, tbe.getForgUnitId()));
				}
				
				if (tbe.getForgCode()!=null && !"".equals(tbe.getForgCode())) {
					Path<String> forgCode = root.get("forgCode");
					predicates.add(bulider.equal(forgCode, tbe.getForgCode()));
				}
				
				if (tbe.getFbasinId()!=null && !"".equals(tbe.getFbasinId())) {
					Path<String> fbasinId = root.get("fbasinId");
					predicates.add(bulider.equal(fbasinId, tbe.getFbasinId()));
				}
				
				if (tbe.getFcountyId()!=null && !"".equals(tbe.getFcountyId())) {
					Path<String> fcountyId = root.get("fcountyId");
					predicates.add(bulider.equal(fcountyId, tbe.getFcountyId()));
				}
				
				if (tbe.getFindustryTypeId()!=null && !"".equals(tbe.getFindustryTypeId())) {
					Path<String> findustryTypeId = root.get("findustryTypeId");
					predicates.add(bulider.equal(findustryTypeId, tbe.getFindustryTypeId()));
				}
				
				if(null != tbe.getFenterId() && !"".equals(tbe.getFenterId().trim())) {
					predicates.add(bulider.equal(root.get("fenterId"), tbe.getFenterId()));
				}
				
				if(null != tbe.getFlcList() && tbe.getFlcList().size() > 0) {
					predicates.add(bulider.in(root.get("forgUnitId")).value(tbe.getFlcList()));
				}

				return predicate;
			}
		};
	}
}
