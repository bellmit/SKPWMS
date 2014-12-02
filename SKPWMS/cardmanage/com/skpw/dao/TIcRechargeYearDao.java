package com.skpw.dao;

import java.util.List;
import java.util.Map;

public interface TIcRechargeYearDao {

	public List<Map<String, Object>> findlastxkpflByqyid(int page, int rows, String qyid);
}
