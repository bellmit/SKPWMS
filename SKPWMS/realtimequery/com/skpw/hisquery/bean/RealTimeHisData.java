package com.skpw.hisquery.bean;

/**
 * @author hjy
 * 
 *         废水历史记录查询 2014-10-9
 */
public class RealTimeHisData {
	private String fid;// 总量控制器id
	private String fconname;// 总量控制器名字
	private String time;// 时间
	private Double codC;// cod浓度
	private Double codD;// cod实时排放量
	private Double codS;// cod余量
	private Double nh3C;// nh3浓度
	private Double nh3D;// nh3实时排放量
	private Double nh3S;// nh3余量
	private Double flow;// 实时流量

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getCodC() {
		return codC;
	}

	public void setCodC(Double codC) {
		this.codC = codC;
	}

	public Double getCodD() {
		return codD;
	}

	public void setCodD(Double codD) {
		this.codD = codD;
	}

	public Double getCodS() {
		return codS;
	}

	public void setCodS(Double codS) {
		this.codS = codS;
	}

	public Double getNh3C() {
		return nh3C;
	}

	public void setNh3C(Double nh3c) {
		nh3C = nh3c;
	}

	public Double getNh3D() {
		return nh3D;
	}

	public void setNh3D(Double nh3d) {
		nh3D = nh3d;
	}

	public Double getNh3S() {
		return nh3S;
	}

	public void setNh3S(Double nh3s) {
		nh3S = nh3s;
	}

	public Double getFlow() {
		return flow;
	}

	public void setFlow(Double flow) {
		this.flow = flow;
	}

	public String getFconname() {
		return fconname;
	}

	public void setFconname(String fconname) {
		this.fconname = fconname;
	}

}
