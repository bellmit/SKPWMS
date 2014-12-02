package com.skpw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skpw.bean.TBasEnterprise;
import com.skpw.bean.TIcRechargeYear;
import com.skpw.bean.TPsPermits;
import com.skpw.dao.TIcRechargeYearDao;
import com.skpw.repository.TIcRechargeRepository;
import com.skpw.repository.TIcRechargeYearRepository;
import com.skpw.repository.TPsPermitsRepository;

@Service
@Transactional
public class TIcRechargeYearServiceImpl implements TIcRechargeYearService{

	@Autowired
	private TIcRechargeRepository tIcRechargeRepository;
	
	@Autowired
	private TIcRechargeDetailsService tIcRechargeDetailsService;
	
	@Autowired
	private TIcRechargeYearRepository tIcRechargeYearRepository;
	
	@Autowired
	private TPsPermitsRepository tPsPermitsRepository;
	
	@Autowired
	private TIcRechargeYearDao tIcRechargeYearDao;
	
	@Override
	public Page<TIcRechargeYear> findAllByPage(TIcRechargeYear tIcRechargeYear,TBasEnterprise tBasEnterprise,Date rechargeStart,Date rechargeEnd,Long rechargeYearStart,Long rechargeYearEnd,Pageable pager) {
		return tIcRechargeYearRepository.findAll(buildSpecification(tIcRechargeYear,tBasEnterprise,rechargeStart,rechargeEnd,rechargeYearStart,rechargeYearEnd),pager);
	}

	@Override
	public TIcRechargeYear findOne(String id) {
		return tIcRechargeYearRepository.findOne(id);
	}

	@Override
	public TIcRechargeYear save(TIcRechargeYear tIcRechargeYear) {
		return tIcRechargeYearRepository.save(tIcRechargeYear);
	}

	@Override
	public TIcRechargeYear save(TIcRechargeYear tIcRechargeYear,
			TPsPermits tPsPermits) {
		tPsPermitsRepository.save(tPsPermits);
//		tIcRechargeRepository.save(tIcRechargeYear.gettIcRecharge());
//		tIcRechargeDetailsService.save(tIcRechargeYear.gettIcRechargeDetails());
		if (null==tIcRechargeYear.gettIcRecharge().getfRechargeID()) {
			tIcRechargeRepository.save(tIcRechargeYear.gettIcRecharge());
		}
		return tIcRechargeYearRepository.save(tIcRechargeYear);
	}
	
	@Override
	public List<TIcRechargeYear> save(List<TIcRechargeYear> tIcRechargeYears,
			List<TPsPermits> tPsPermitss) {
		tPsPermitsRepository.save(tPsPermitss);
		if (null==tIcRechargeYears.get(0).gettIcRecharge().getfRechargeID()) {
			tIcRechargeRepository.save(tIcRechargeYears.get(0).gettIcRecharge());
		}
		return tIcRechargeYearRepository.save(tIcRechargeYears);
	}

	
	@Override
	public void update(TIcRechargeYear tIcRechargeYear) {
//		TIcRechargeYear _tIcRechargeYear=tIcRechargeYearRepository.findOne(tIcRechargeYear.getId());
//		_tIcRechargeYear.setName(tIcRechargeYear.getName());
//		_tIcRechargeYear.setClassify(tIcRechargeYear.getClassify());
//		_tIcRechargeYear.setCy(tIcRechargeYear.getCy());
//		_tIcRechargeYear.setFh(tIcRechargeYear.getFh());
//		_tIcRechargeYear.setJkwh(tIcRechargeYear.getJkwh());
//		_tIcRechargeYear.setLlyjcl(tIcRechargeYear.getLlyjcl());
//		_tIcRechargeYear.setWhxz(tIcRechargeYear.getWhxz());
//		_tIcRechargeYear.setWxtx(tIcRechargeYear.getWxtx());
//		tIcRechargeYearRepository.save(_tIcRechargeYear);
	}

	@Override
	public void delete(String id) {
		tIcRechargeYearRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tIcRechargeYearRepository.delete(id);
		}
	}
	
	public TIcRechargeYearRepository getTIcRechargeYearRepository() {
		return tIcRechargeYearRepository;
	}

	public void setTIcRechargeYearRepository(TIcRechargeYearRepository tIcRechargeYearRepository) {
		this.tIcRechargeYearRepository = tIcRechargeYearRepository;
	}

	private Specification<TIcRechargeYear> buildSpecification(final TIcRechargeYear tIcRechargeYear, final TBasEnterprise tBasEnterprise, 
			final Date rechargeStart, final Date rechargeEnd, final Long rechargeYearStart, final Long rechargeYearEnd) {
		return new Specification<TIcRechargeYear>() {

			@Override
			public Predicate toPredicate(Root<TIcRechargeYear> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				/*if(null!=tIcRechargeYear.getName() && !"".equals(tIcRechargeYear.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tIcRechargeYear.getName()+"%"));
				}
				
				if(null!=tIcRechargeYear.getClassify() && !"".equals(tIcRechargeYear.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tIcRechargeYear.getClassify()+"%"));
				}*/
				
				if(null!=tIcRechargeYear){
					
					if( null!=tIcRechargeYear.gettIcRecharge() ){
						if(StringUtils.isNotEmpty(tIcRechargeYear.gettIcRecharge().getfRechargeNo())){
							Path<String> path=root.get("tIcRecharge").get("fRechargeNo");
							predicates.add(cb.equal(path, tIcRechargeYear.gettIcRecharge().getfRechargeNo()));
						}
						//企业名称
						if(null!=tIcRechargeYear.gettIcRecharge().gettBasEnterprise() && StringUtils.isNotEmpty(tIcRechargeYear.gettIcRecharge().gettBasEnterprise().getFenterName() )){
							Path<String> path=root.get("tIcRecharge").get("tBasEnterprise").get("fenterName");
							predicates.add(cb.like(path, "%"+tIcRechargeYear.gettIcRecharge().gettBasEnterprise().getFenterName()+"%"));
						}
					}
					
					if(null!=tIcRechargeYear.gettBasPollutant() && StringUtils.isNotEmpty(tIcRechargeYear.gettBasPollutant().getfPollutantID())){
						Path<String> path=root.get("tBasPollutant").get("fPollutantID");
						predicates.add(cb.equal(path, tIcRechargeYear.gettBasPollutant().getfPollutantID()));
					}
					
					
				}

				if(null!=rechargeStart){
					Path<Date> path=root.get("tIcRecharge").get("fDate");
					predicates.add(cb.greaterThanOrEqualTo(path, rechargeStart));
				}
				
				if(null!=rechargeEnd){
					Path<Date> path=root.get("tIcRecharge").get("fDate");
					predicates.add(cb.lessThanOrEqualTo(path, rechargeEnd));
				}
				
				if(null!=rechargeYearStart){
					Path<Long> path=root.get("fYearID");
					predicates.add(cb.greaterThanOrEqualTo(path, rechargeYearStart));
				}
				
				if(null!=rechargeYearEnd){
					Path<Long> path=root.get("fYearID");
					predicates.add(cb.lessThanOrEqualTo(path, rechargeYearEnd));
				}
				
				
				/*if(null!=tBasEnterprise && null!=tBasEnterprise.getFenterId() && !"".equals(tBasEnterprise.getFenterId())){
					Path<String> path=root.get("tIcRecharge").get("tBasEnterprise").get("fenterId");
					predicates.add(cb.equal(path, tBasEnterprise.getFenterId()));
				}*/
				
				if(predicates.size()>0){
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				}else{
					query.where(cb.conjunction());
				}
				
				return query.getRestriction();
			}
			
		};
	}

	@Override
	public Page<TIcRechargeYear> findczxxByqyid(TBasEnterprise tBasEnterprise,
			Pageable pager) {
		return tIcRechargeYearRepository.findAll(buildSpecificationczxx(tBasEnterprise), pager);
	}

	private Specification<TIcRechargeYear> buildSpecificationczxx(final TBasEnterprise tBasEnterprise) {
		return new Specification<TIcRechargeYear>() {

			@Override
			public Predicate toPredicate(Root<TIcRechargeYear> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
		
				if(null!=tBasEnterprise && null!=tBasEnterprise.getFenterId() && !"".equals(tBasEnterprise.getFenterId())){
					Path<String> path=root.get("tIcRecharge").get("tBasEnterprise").get("fenterId");
					predicates.add(cb.equal(path, tBasEnterprise.getFenterId()));
				}
				
				if(predicates.size()>0){
					query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
				}else{
					query.where(cb.conjunction());
				}
				
				return query.getRestriction();
			}
			
		};
	}

	@Override
	public List<Map<String, Object>> findlastxkpflByqyid(int page, int rows,
			String qyid) {
		return tIcRechargeYearDao.findlastxkpflByqyid(page, rows, qyid);
	}
	
}
