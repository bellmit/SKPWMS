package com.skpw.common;

import java.util.UUID;

/**
 * 生成32位UUID
 * @author Que_P
 *
 */
public class CreateUUID {
	
	public static String getUuid(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		id = id.replace("-", "");
		return id;
	}

}