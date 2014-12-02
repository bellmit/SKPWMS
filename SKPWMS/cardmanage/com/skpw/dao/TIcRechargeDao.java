package com.skpw.dao;

import java.util.List;

public interface TIcRechargeDao {

	public List<String> findTicRechargeBycardinfoId(String cardinfoid);
}
