package com.skpw.bean;

public class ReportData {

	private String time;// 时间
	private String codC;// cod浓度
	private String codF;// cod流量
	private String nh3C;// nh3浓度
	private String nh3F;// nh3流量
	private String flow;// 总流量
	private String codA;//核对值
	private String nh3A;//核对值

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCodC() {
		return codC;
	}

	public void setCodC(String codC) {
		this.codC = codC;
	}

	public String getCodF() {
		return codF;
	}

	public void setCodF(String codF) {
		this.codF = codF;
	}

	public String getNh3C() {
		return nh3C;
	}

	public void setNh3C(String nh3c) {
		nh3C = nh3c;
	}

	public String getNh3F() {
		return nh3F;
	}

	public void setNh3F(String nh3f) {
		nh3F = nh3f;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getCodA() {
		return codA;
	}

	public void setCodA(String codA) {
		this.codA = codA;
	}

	public String getNh3A() {
		return nh3A;
	}

	public void setNh3A(String nh3a) {
		nh3A = nh3a;
	}
	
}
