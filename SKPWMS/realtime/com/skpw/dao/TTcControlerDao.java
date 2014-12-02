package com.skpw.dao;

import java.util.List;
import java.util.Map;

public interface TTcControlerDao {

	public List<Map<String, Object>> getMapList(String id);
	
	public List<Map<String, Object>> findTTcControlersOfWry(String id);
	
}
