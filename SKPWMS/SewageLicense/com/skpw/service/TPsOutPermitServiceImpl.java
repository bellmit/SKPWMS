package com.skpw.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.TPsOutPermit;
import com.skpw.repository.TPsOutPermitRepository;

@Service
public class TPsOutPermitServiceImpl implements TPsOutPermitService{

	@Autowired
	private TPsOutPermitRepository tPsOutPermitRepository;
	
	@Override
	public Page<TPsOutPermit> findAllByPage(TPsOutPermit tPsOutPermit,Pageable pager) {
		return tPsOutPermitRepository.findAll(buildSpecification(tPsOutPermit),pager);
	}

	@Override
	public TPsOutPermit findOne(String id) {
		return tPsOutPermitRepository.findOne(id);
	}

	@Override
	public TPsOutPermit save(TPsOutPermit tPsOutPermit) {
		return tPsOutPermitRepository.save(tPsOutPermit);
	}

	@Override
	public void update(TPsOutPermit tPsOutPermit) {
//		TPsOutPermit _tPsOutPermit=tPsOutPermitRepository.findOne(tPsOutPermit.getId());
//		_tPsOutPermit.setName(tPsOutPermit.getName());
//		_tPsOutPermit.setClassify(tPsOutPermit.getClassify());
//		_tPsOutPermit.setCy(tPsOutPermit.getCy());
//		_tPsOutPermit.setFh(tPsOutPermit.getFh());
//		_tPsOutPermit.setJkwh(tPsOutPermit.getJkwh());
//		_tPsOutPermit.setLlyjcl(tPsOutPermit.getLlyjcl());
//		_tPsOutPermit.setWhxz(tPsOutPermit.getWhxz());
//		_tPsOutPermit.setWxtx(tPsOutPermit.getWxtx());
//		tPsOutPermitRepository.save(_tPsOutPermit);
	}

	@Override
	public void delete(String id) {
		tPsOutPermitRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tPsOutPermitRepository.delete(id);
		}
	}
	
	public TPsOutPermitRepository getTPsOutPermitRepository() {
		return tPsOutPermitRepository;
	}

	public void setTPsOutPermitRepository(TPsOutPermitRepository tPsOutPermitRepository) {
		this.tPsOutPermitRepository = tPsOutPermitRepository;
	}

	private Specification<TPsOutPermit> buildSpecification(final TPsOutPermit tPsOutPermit) {
		return new Specification<TPsOutPermit>() {

			@Override
			public Predicate toPredicate(Root<TPsOutPermit> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				/*if(null!=tPsOutPermit.getName() && !"".equals(tPsOutPermit.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tPsOutPermit.getName()+"%"));
				}
				
				if(null!=tPsOutPermit.getClassify() && !"".equals(tPsOutPermit.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tPsOutPermit.getClassify()+"%"));
				}*/
				
				if(null!=tPsOutPermit.gettBasEnterprise()){
					
					if(null!=tPsOutPermit.gettBasEnterprise().getFenterName() && !"".equals(tPsOutPermit.gettBasEnterprise().getFenterName())){
						Path<String> path=root.get("tBasEnterprise").get("fenterName");
						predicates.add(cb.like(path, "%"+tPsOutPermit.gettBasEnterprise().getFenterName()+"%"));
					}
					
					if(null!=tPsOutPermit.gettBasEnterprise().getFenterId() && !"".equals(tPsOutPermit.gettBasEnterprise().getFenterId())){
						Path<String> path=root.get("tBasEnterprise").get("fenterId");
						predicates.add(cb.equal(path, tPsOutPermit.gettBasEnterprise().getFenterId()));
					}
					
					if(null!=tPsOutPermit.gettBasEnterprise().getForgUnitId() && !"".equals(tPsOutPermit.gettBasEnterprise().getForgUnitId())){
						Path<String> path=root.get("tBasEnterprise").get("forgUnitId");
						predicates.add(cb.like(path, "%"+tPsOutPermit.gettBasEnterprise().getForgUnitId()+"%"));
					}
					
					if(null!=tPsOutPermit.gettBasEnterprise().getForgCode() && !"".equals(tPsOutPermit.gettBasEnterprise().getForgCode())){
						Path<String> path=root.get("tBasEnterprise").get("forgCode");
						predicates.add(cb.like(path, "%"+tPsOutPermit.gettBasEnterprise().getForgCode()+"%"));
					}
					
					if(null!=tPsOutPermit.gettBasEnterprise().getFcountyId() && !"".equals(tPsOutPermit.gettBasEnterprise().getFcountyId())){
						Path<String> path=root.get("tBasEnterprise").get("fcountyId");
						predicates.add(cb.like(path, "%"+tPsOutPermit.gettBasEnterprise().getFcountyId()+"%"));
					}
					
					if(null!=tPsOutPermit.gettBasEnterprise().getFindustryTypeId() && !"".equals(tPsOutPermit.gettBasEnterprise().getFindustryTypeId())){
						Path<String> path=root.get("tBasEnterprise").get("findustryTypeId");
						predicates.add(cb.like(path, "%"+tPsOutPermit.gettBasEnterprise().getFindustryTypeId()+"%"));
					}
				}
				
				
				if(null!=tPsOutPermit.getfIsSewage()){
					Path<String> path=root.get("fIsSewage");
					predicates.add(cb.equal(path, tPsOutPermit.getfIsSewage()));
				}
				
				if(null!=tPsOutPermit.getfIsWasteGas()){
					Path<String> path=root.get("fIsWasteGas");
					predicates.add(cb.equal(path, tPsOutPermit.getfIsWasteGas()));
				}
				
				if(null!=tPsOutPermit.getfIsNoise()){
					Path<String> path=root.get("fIsNoise");
					predicates.add(cb.equal(path, tPsOutPermit.getfIsNoise()));
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
	public TPsOutPermit findByEnterpriseId(String id) {
		return tPsOutPermitRepository.findByEnterpriseId(id,new Date());
	}

	public List<TPsOutPermit> findPermitByEnterId(String enterId)
	{
		return tPsOutPermitRepository.findPermitByEnterId(enterId);
	}
}
