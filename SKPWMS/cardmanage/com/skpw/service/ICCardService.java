package com.skpw.service;

import java.util.List;

import com.skpw.bean.ICCard;

public interface ICCardService {
	
	//保存ic卡
	public ICCard saveICCard(ICCard icCard);
	
	//查找
	public ICCard findOne(String iccardid);
	
	public ICCard findIcCardByphyNo(String cardPhyNo);
	
	public int updateIccStateByphyNo(String cardPhyNo);
	
	public List<ICCard> findAll();
	
	public List<String> findTicRechargeBycardinfoId(String cardinfoid);

}
