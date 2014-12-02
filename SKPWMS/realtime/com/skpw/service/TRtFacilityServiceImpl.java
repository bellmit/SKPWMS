package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TRtFacility;
import com.skpw.bean.TSysRole;
import com.skpw.repository.TRtFacilityRepositoty;

@Service("tRtFacilityService")
public class TRtFacilityServiceImpl implements TRtFacilityService {

	@Autowired
	private TRtFacilityRepositoty tRtFacilityRepositoty;

	@Override
	public Page<TRtFacility> findAllByPage(String ffacilityNo,
			String ffacilityName, String wry_id, int page, int rows) {
		// TODO Auto-generated method stub
		Pageable pager = new PageRequest(page - 1, rows);
		return tRtFacilityRepositoty.findAll(
				buildSpecification(ffacilityNo, ffacilityName, wry_id), pager);
	}

	private Specification<TRtFacility> buildSpecification(
			final String ffacilityNo, final String ffacilityName,
			final String wry_id) {
		return new Specification<TRtFacility>() {

			@Override
			public Predicate toPredicate(Root<TRtFacility> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (null != ffacilityNo && "" != ffacilityNo.trim()
						&& !ffacilityNo.isEmpty()) {
					predicates.add(cb.like(
							root.get("ffacilityNo").as(String.class), "%"
									+ ffacilityNo + "%"));
				}
				if (null != ffacilityName && "" != ffacilityName.trim()
						&& !ffacilityName.isEmpty()) {
					predicates.add(cb.like(
							root.get("ffacilityName").as(String.class), "%"
									+ ffacilityName + "%"));
				}
				if (null != wry_id && "" != wry_id.trim() && !wry_id.isEmpty()) {
					predicates.add(cb.equal(
							root.get("fenterId").as(String.class), wry_id));
				}
				if (predicates.size() > 0) {
					query.where(cb.and(predicates
							.toArray(new Predicate[predicates.size()])));
				} else {
					query.where(cb.conjunction());
				}
				return query.getRestriction();
			}

		};

	}

	@Override
	public void save(TRtFacility tRtFacility) {

		tRtFacilityRepositoty.save(tRtFacility);
	}

	@Override
	public TRtFacility findOne(Integer id) {
		return tRtFacilityRepositoty.findOne(id);
	}

	@Override
	public void del(String ids) {

		if (ids != null && ids.trim() != "") {
			String[] idArr = ids.split(",");
			for (int i = 0; i < idArr.length; i++) {
				tRtFacilityRepositoty.delete(Integer.valueOf(idArr[i]));
			}
		}
	}

	public List<TRtFacility> findFaciltityByControlid(int fctrlid) {
		List list = tRtFacilityRepositoty.findFaciltityByControlid(fctrlid);
		List<TRtFacility> facilities = new ArrayList<TRtFacility>();
		for (int i = 0; i < list.size(); i++) {
			TRtFacility facility = new TRtFacility();
			Object[] objs = (Object[]) list.get(i);
			facility.setFfacilityId((Integer) objs[0]);
			facility.setFfacilityName((String) objs[1]);
			facilities.add(facility);
		}

		return facilities;
	}

}
