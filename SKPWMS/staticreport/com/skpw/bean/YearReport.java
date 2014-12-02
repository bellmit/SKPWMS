package com.skpw.bean;

/**
 * @author hjy 年统计报表
 */
public class YearReport {

	private Integer ttcid;// 总量控制器fid
	private String date;// 统计时间
	private int month;// 月
	private float cod;// cod浓度
	private float nh3;// nh3浓度

	private float codf;// cod排放量
	private float nh3f;// nh3排放量

	private float coda;//cody与nh3核准量

	private float nh3a;

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

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
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

	public float getCoda() {
		return coda;
	}

	public void setCoda(float coda) {
		this.coda = coda;
	}

	public float getNh3a() {
		return nh3a;
	}

	public void setNh3a(float nh3a) {
		this.nh3a = nh3a;
	}
	
	
}
