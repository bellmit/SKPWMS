package com.skpw.service;

import java.util.ArrayList;
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

import com.skpw.bean.TBasPollutant;
import com.skpw.bean.TPsOutSPoll;
import com.skpw.bean.TPsOutWGPoll;
import com.skpw.repository.TBasPollutantRepository;
import com.skpw.repository.TPsOutSPollRepository;
import com.skpw.repository.TPsOutSewageRepository;

@Service
public class TBasPollutantServiceImpl implements TBasPollutantService{

	@Autowired
	private TBasPollutantRepository tBasPollutantRepository;
	
	@Autowired
	private TPsOutSPollRepository tPsOutSPollRepository;

	@Override
	public List<TBasPollutant> findAll(TBasPollutant tBasPollutant) {
//		return tBasPollutantRepository.findAll();
		return tBasPollutantRepository.findAll(buildSpecification(tBasPollutant));
	}
	
	@Override
	public Page<TBasPollutant> findAllByPage(TBasPollutant tBasPollutant,Pageable pager) {
		return tBasPollutantRepository.findAll(buildSpecification(tBasPollutant),pager);
	}

	@Override
	public TBasPollutant findOne(String id) {
		return tBasPollutantRepository.findOne(id);
	}

	@Override
	public TBasPollutant save(TBasPollutant tBasPollutant) {
		return tBasPollutantRepository.save(tBasPollutant);
	}

	@Override
	public void update(TBasPollutant tBasPollutant) {
//		TBasPollutant _tBasPollutant=tBasPollutantRepository.findOne(tBasPollutant.getId());
//		_tBasPollutant.setName(tBasPollutant.getName());
//		_tBasPollutant.setClassify(tBasPollutant.getClassify());
//		_tBasPollutant.setCy(tBasPollutant.getCy());
//		_tBasPollutant.setFh(tBasPollutant.getFh());
//		_tBasPollutant.setJkwh(tBasPollutant.getJkwh());
//		_tBasPollutant.setLlyjcl(tBasPollutant.getLlyjcl());
//		_tBasPollutant.setWhxz(tBasPollutant.getWhxz());
//		_tBasPollutant.setWxtx(tBasPollutant.getWxtx());
//		tBasPollutantRepository.save(_tBasPollutant);
	}

	@Override
	public void delete(String id) {
		tBasPollutantRepository.delete(id);
	}

	@Override
	public void deleteList(List<String> ids) {
		for (String id : ids) {
			tBasPollutantRepository.delete(id);
		}
	}
	
	public TBasPollutantRepository getTBasPollutantRepository() {
		return tBasPollutantRepository;
	}

	public void setTBasPollutantRepository(TBasPollutantRepository tBasPollutantRepository) {
		this.tBasPollutantRepository = tBasPollutantRepository;
	}

	private Specification<TBasPollutant> buildSpecification(final TBasPollutant tBasPollutant) {
		return new Specification<TBasPollutant>() {

			@Override
			public Predicate toPredicate(Root<TBasPollutant> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				/*if(null!=tBasPollutant.getName() && !"".equals(tBasPollutant.getName())){
					Path<String> path=root.get("name");
					predicates.add(cb.like(path, "%"+tBasPollutant.getName()+"%"));
				}
				
				if(null!=tBasPollutant.getClassify() && !"".equals(tBasPollutant.getClassify())){
					Path<String> path=root.get("classify");
					predicates.add(cb.like(path, "%"+tBasPollutant.getClassify()+"%"));
				}*/
				
				if(null!=tBasPollutant){
					if (null!=tBasPollutant.gettBasPollutantType()) {
						if (StringUtils.isNotEmpty(tBasPollutant.gettBasPollutantType().getfPollTypeID())) {
							Path<String> path=root.get("tBasPollutantType").get("fPollTypeID");
							predicates.add(cb.like(path, tBasPollutant.gettBasPollutantType().getfPollTypeID()));
						}
						if (StringUtils.isNotEmpty(tBasPollutant.gettBasPollutantType().getfPollTypeCode())) {
							Path<String> path=root.get("tBasPollutantType").get("fPollTypeCode");
							predicates.add(cb.like(path, tBasPollutant.gettBasPollutantType().getfPollTypeCode()));
						}
					}
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
	public List<Map<String, String>> findAllByOutSPoll(TPsOutSPoll tPsOutSPoll) {
		return tPsOutSPollRepository.findPollutantByOutSewage(tPsOutSPoll.gettPsOutSewage().getfOutSewageID());
	}

	@Override
	public List<Map<String, String>> findAllByTPsOutWGPoll(
			TPsOutWGPoll tPsOutWGPoll) {
		return tPsOutSPollRepository.findPollutantBytPsWasteGasOutlet(tPsOutWGPoll.gettPsWasteGasOutlet().getfWGOutletID());
	}

}
