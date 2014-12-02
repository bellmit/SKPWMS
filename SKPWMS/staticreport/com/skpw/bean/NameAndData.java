package com.skpw.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hjy 统计的数据名称和数据
 */
public class NameAndData {

	private String name;  //统计的名字

	private List data = new ArrayList();//数据

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

}
