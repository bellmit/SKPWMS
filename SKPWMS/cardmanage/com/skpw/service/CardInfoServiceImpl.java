package com.skpw.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.skpw.bean.CardInfo;
import com.skpw.repository.CardInfoRepository;

@Service("cardInfoService")
public class CardInfoServiceImpl implements CardInfoService {
	@Resource
	private CardInfoRepository cardInfoRepository;

	public void saveCard(CardInfo cardinfo) {

		cardInfoRepository.save(cardinfo);
	}

	public List<CardInfo> findCardInfoByEnterId(String enterId)
	{
		return cardInfoRepository.findCardInfoByEnterId(enterId);
	}

	
	public CardInfo findCardById(String cardid) {
		
		return cardInfoRepository.findOne(cardid);
	}

	
	public CardInfo findCardByCardId(String cardid) {
		return cardInfoRepository.findByCardid(cardid);
	}

	
	public CardInfo findCardByCardPhyNo(String cardPhyNo) {
		return cardInfoRepository.findByCardPhyNo(cardPhyNo);
	}

	
	public List<CardInfo> showCardinfo() {
		 
		return cardInfoRepository.findAll();
	}

	
	public Page<CardInfo> showCardinfoByCondition(Specification<CardInfo> spec,
			Pageable pager) {
		 
		return cardInfoRepository.findAll(spec, pager);
	}

	
	public void delCardInfo(String id) {
		 
		cardInfoRepository.delete(id);
	}

	
	public long count() {
		
		return cardInfoRepository.count();
	}

}
