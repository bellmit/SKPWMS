package com.skpw.bean;

/**
 * @author Administrator
 *	监控参数列表
 */
public class ParamsBean {

	private Integer id;//监控参数编号

	private String name;//监控参数名称

	private String unit;//监控参数单位

	public ParamsBean() {
		super();
	}

	public ParamsBean(Integer id, String name, String unit) {
		super();
		this.id = id;
		this.name = name;
		this.unit = unit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
