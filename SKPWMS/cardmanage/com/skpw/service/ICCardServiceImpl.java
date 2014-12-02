package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.skpw.bean.ICCard;
import com.skpw.dao.TIcRechargeDao;
import com.skpw.repository.ICCardRepository;

@Service("icCardService")
public class ICCardServiceImpl implements ICCardService {

	@Resource
	private ICCardRepository icCardRepository;
	
	@Resource
	private TIcRechargeDao tIcRechargeDao;

	public ICCard saveICCard(ICCard icCard) {

		return icCardRepository.save(icCard);

	}

	
	public ICCard findOne(String iccardid) {
		
		return icCardRepository.findOne(iccardid);
	}


	public ICCard findIcCardByphyNo(String cardPhyNo) {
		List<ICCard> list = icCardRepository.findIcCardByphyNo(cardPhyNo);
		if(list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public int updateIccStateByphyNo(String cardPhyNo) {
		return icCardRepository.updateIccStateByphyNo(cardPhyNo);
	}


	@Override
	public List<ICCard> findAll() {
		return icCardRepository.findAll();
	}


	@Override
	public List<String> findTicRechargeBycardinfoId(String cardinfoid) {
		return tIcRechargeDao.findTicRechargeBycardinfoId(cardinfoid);
	}

}
