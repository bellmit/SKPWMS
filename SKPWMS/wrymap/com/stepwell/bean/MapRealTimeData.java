package com.stepwell.bean;

import java.util.List;

import com.skpw.bean.RealtimeBean;

public class MapRealTimeData {

	private String wryid;// 企业id
	private float flatitude;// 精度
	private float flongitude;// 维度
	private String wry;// 污染源名称
	private String name;// 总量控制器名称
	private String codC;// cod浓度
	private String time;// 时间
	private String codD;// cod实时排放量
	private String codS;// cod余量
	private String nh3C;// nh3浓度
	private String nh3D;// nh3实时排放量
	private String nh3S;// nh3余量
	private String flow;// 实时流量
	private String codFAS;
	private String nh3FAS;
	private int faState;
	private String fpollTypeCode;
	private List<RealtimeBean> fqlist;

	public String getWryid() {
		return wryid;
	}

	public void setWryid(String wryid) {
		this.wryid = wryid;
	}

	public float getFlatitude() {
		return flatitude;
	}

	public void setFlatitude(float flatitude) {
		this.flatitude = flatitude;
	}

	public float getFlongitude() {
		return flongitude;
	}

	public void setFlongitude(float flongitude) {
		this.flongitude = flongitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public String getCodD() {
		return codD;
	}

	public void setCodD(String codD) {
		this.codD = codD;
	}

	public String getCodS() {
		return codS;
	}

	public void setCodS(String codS) {
		this.codS = codS;
	}

	public String getNh3C() {
		return nh3C;
	}

	public void setNh3C(String nh3c) {
		nh3C = nh3c;
	}

	public String getNh3D() {
		return nh3D;
	}

	public void setNh3D(String nh3d) {
		nh3D = nh3d;
	}

	public String getNh3S() {
		return nh3S;
	}

	public void setNh3S(String nh3s) {
		nh3S = nh3s;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getWry() {
		return wry;
	}

	public void setWry(String wry) {
		this.wry = wry;
	}

	public String getCodFAS() {
		return codFAS;
	}

	public void setCodFAS(String codFAS) {
		this.codFAS = codFAS;
	}

	public String getNh3FAS() {
		return nh3FAS;
	}

	public void setNh3FAS(String nh3fas) {
		nh3FAS = nh3fas;
	}

	public int getFaState() {
		return faState;
	}

	public void setFaState(int faState) {
		this.faState = faState;
	}

	public String getFpollTypeCode() {
		return fpollTypeCode;
	}

	public void setFpollTypeCode(String fpollTypeCode) {
		this.fpollTypeCode = fpollTypeCode;
	}

	public List<RealtimeBean> getFqlist() {
		return fqlist;
	}

	public void setFqlist(List<RealtimeBean> fqlist) {
		this.fqlist = fqlist;
	}

}
