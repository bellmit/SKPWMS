package com.skpw.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;



public class ConvertJson {
	
	
	//对象转json
	public static Object objectToJson(Object object) {
		
		Object object1=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			object1=mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object1;
	}
	
	//list转json
	public static  Object  listToJson(List list){
		
		Object object=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			object=mapper.writeValueAsString(list);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	
	//map转json
	public static Object MapToJson(Map map){
		Object object=null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			object=mapper.writeValueAsString(map);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return object;
	}
	
	//数组转json
	public static Object arrarToJson(String[] arr){
		Object object=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			object=mapper.writeValueAsString(arr);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return object;
	}
	
	//字符转json
	public static Object stringToJson(String str){
		
		Object object=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			object=mapper.writeValueAsString(str);
		} catch (JsonGenerationException e) {
			
			e.printStackTrace();
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return object;
	}
	
	
	
}
