package com.skpw.bean;

/**
 * @author hjy
 * 日报表统计
 */
public class DayReport {
	
	private Integer ttcid;//总量控制器fid
	private String date;//统计时间
	private int hour;//小时
	private float cod;//cod浓度
	private float nh3;//nh3浓度
	private float codpfl;//cod排放量
	private float nh3pfl;//nh3排放量
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
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
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
	public float getCodpfl() {
		return codpfl;
	}
	public void setCodpfl(float codpfl) {
		this.codpfl = codpfl;
	}
	public float getNh3pfl() {
		return nh3pfl;
	}
	public void setNh3pfl(float nh3pfl) {
		this.nh3pfl = nh3pfl;
	}
	
	

}
