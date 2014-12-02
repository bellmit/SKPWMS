package com.skpw.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.skpw.bean.CardInfo;
import com.skpw.bean.TSysUserInfo;

public interface CardInfoService {
	
	//保存IC卡信息
	public void saveCard(CardInfo cardinfo);
	
	public List<CardInfo> findCardInfoByEnterId(String enterId);
	
	//获取卡的信息
	public CardInfo  findCardById(String cardid);
	
	//获取卡的信息
	public CardInfo  findCardByCardId(String cardid);
	
	//获取卡的信息
	public CardInfo  findCardByCardPhyNo(String cardPhyNo);
	
	//获取所有信息
	public List<CardInfo> showCardinfo();
	
	//分页和条件查询
	public Page<CardInfo> showCardinfoByCondition(Specification<CardInfo> spec,Pageable pager);
	
	//删除卡的基本信息
	public void  delCardInfo(String id);
	//统计条数
	public long count();
}
