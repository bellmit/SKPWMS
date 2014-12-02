package com.skpw.bean;

/**
 * @author Administrator 实时数据显示列表
 */
public class RealtimeBean {
	
	private Integer ctrlid; // 控制器id
	private String ctrlname;//控制器名称
	private String monitertime; // 监控时间
	private String pfkname;//排放口名称
	private float O2Rtd; // 实测SO2
	private float O1Rtd; // 实测烟尘
	private float SO1Rtd; // 氧气百分比
	private float SO2Rtd; // 流速
	private float SO3Rtd; // 烟气温度
	private float O3Rtd; // 实测NOx
	private float O4Rtd; // co浓度
	private float BO2Rtd; // 废气
	private float SO8Rtd; // 烟气压力
	private float O2ZSRtd; // 折算so2
	private float O1ZSRtd; // 折算烟尘
	private float O3ZSRtd; // 折算Nox
	private float SO7Rtd;//烟道截面积	
	private float SO6Rtd;//制冷温度
	private float SO5Rtd;//烟气湿度
	private float SO4Rtd;//烟气动压	
	private  float discharge;//排放量	
	private float zsdischarge;//折算排放量
	private float candischarge;//许可排放量
	private float surplus;//剩余排放量
	
	private String O1FAS;//报警状态值判断
	private String O2FAS;
	private String O3FAS;

	public RealtimeBean() {
		super();
	}

	public Integer getCtrlid() {
		return ctrlid;
	}

	public void setCtrlid(Integer ctrlid) {
		this.ctrlid = ctrlid;
	}
	
	
	public String getCtrlname() {
		return ctrlname;
	}

	public void setCtrlname(String ctrlname) {
		this.ctrlname = ctrlname;
	}

	public String getMonitertime() {
		return monitertime;
	}

	public void setMonitertime(String monitertime) {
		this.monitertime = monitertime;
	}

	public String getPfkname() {
		return pfkname;
	}

	public void setPfkname(String pfkname) {
		this.pfkname = pfkname;
	}

	public float getO2Rtd() {
		return O2Rtd;
	}

	public void setO2Rtd(float o2Rtd) {
		O2Rtd = o2Rtd;
	}

	public float getO1Rtd() {
		return O1Rtd;
	}

	public void setO1Rtd(float o1Rtd) {
		O1Rtd = o1Rtd;
	}

	public float getSO1Rtd() {
		return SO1Rtd;
	}

	public void setSO1Rtd(float sO1Rtd) {
		SO1Rtd = sO1Rtd;
	}

	public float getSO2Rtd() {
		return SO2Rtd;
	}

	public void setSO2Rtd(float sO2Rtd) {
		SO2Rtd = sO2Rtd;
	}

	public float getSO3Rtd() {
		return SO3Rtd;
	}

	public void setSO3Rtd(float sO3Rtd) {
		SO3Rtd = sO3Rtd;
	}

	public float getO3Rtd() {
		return O3Rtd;
	}

	public void setO3Rtd(float o3Rtd) {
		O3Rtd = o3Rtd;
	}

	public float getO4Rtd() {
		return O4Rtd;
	}

	public void setO4Rtd(float o4Rtd) {
		O4Rtd = o4Rtd;
	}

	public float getBO2Rtd() {
		return BO2Rtd;
	}

	public void setBO2Rtd(float bO2Rtd) {
		BO2Rtd = bO2Rtd;
	}

	public float getSO8Rtd() {
		return SO8Rtd;
	}

	public void setSO8Rtd(float sO8Rtd) {
		SO8Rtd = sO8Rtd;
	}

	public float getO2ZSRtd() {
		return O2ZSRtd;
	}

	public void setO2ZSRtd(float o2zsRtd) {
		O2ZSRtd = o2zsRtd;
	}

	public float getO1ZSRtd() {
		return O1ZSRtd;
	}

	public void setO1ZSRtd(float o1zsRtd) {
		O1ZSRtd = o1zsRtd;
	}

	public float getO3ZSRtd() {
		return O3ZSRtd;
	}

	public void setO3ZSRtd(float o3zsRtd) {
		O3ZSRtd = o3zsRtd;
	}

	public float getSO7Rtd() {
		return SO7Rtd;
	}

	public void setSO7Rtd(float sO7Rtd) {
		SO7Rtd = sO7Rtd;
	}

	public float getSO6Rtd() {
		return SO6Rtd;
	}

	public void setSO6Rtd(float sO6Rtd) {
		SO6Rtd = sO6Rtd;
	}

	public float getSO5Rtd() {
		return SO5Rtd;
	}

	public void setSO5Rtd(float sO5Rtd) {
		SO5Rtd = sO5Rtd;
	}

	public float getSO4Rtd() {
		return SO4Rtd;
	}

	public void setSO4Rtd(float sO4Rtd) {
		SO4Rtd = sO4Rtd;
	}

	public String getO1FAS() {
		return O1FAS;
	}

	public void setO1FAS(String o1fas) {
		O1FAS = o1fas;
	}

	public String getO2FAS() {
		return O2FAS;
	}

	public void setO2FAS(String o2fas) {
		O2FAS = o2fas;
	}

	public String getO3FAS() {
		return O3FAS;
	}

	public void setO3FAS(String o3fas) {
		O3FAS = o3fas;
	}

	public float getDischarge() {
		return discharge;
	}

	public void setDischarge(float discharge) {
		this.discharge = discharge;
	}

	public float getZsdischarge() {
		return zsdischarge;
	}

	public void setZsdischarge(float zsdischarge) {
		this.zsdischarge = zsdischarge;
	}

	public float getCandischarge() {
		return candischarge;
	}

	public void setCandischarge(float candischarge) {
		this.candischarge = candischarge;
	}

	public float getSurplus() {
		return surplus;
	}

	public void setSurplus(float surplus) {
		this.surplus = surplus;
	}

	

	

}
