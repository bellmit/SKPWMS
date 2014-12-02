package com.skpw.bean;

/**
 * @author hjy
 * 月统计报表
 */
public class MonthReport {
	
	private Integer ttcid;//总量控制器fid
	private String date;//统计时间
	private int day;//天
	private float cod;//cod浓度
	private float nh3;//nh3浓度
	private float codf;//cod排放量
	private float nh3f;//nh3排放量
	public Integer getTtcid() {
		return ttcid;
	}
	public void setTtcid(Integer ttcid) {
		this.ttcid = ttcid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public float getCod() {
		return cod;
	}
	public void setCod(float cod) {
		this.cod = cod;
	}
	public float getNh3() {
		return nh3;
	}
	public void setNh3(float nh3) {
		this.nh3 = nh3;
	}
	public float getCodf() {
		return codf;
	}
	public void setCodf(float codf) {
		this.codf = codf;
	}
	public float getNh3f() {
		return nh3f;
	}
	public void setNh3f(float nh3f) {
		this.nh3f = nh3f;
	}
	
	
}
