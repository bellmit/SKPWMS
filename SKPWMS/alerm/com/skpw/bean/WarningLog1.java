package com.skpw.bean;

public class WarningLog1 {	
	
	private String fwarninglogid;//预警日志ID
	
	private String entername;//企业名称
	
	private  String  ctrname;//控制器名称
	
	private String pollutantname;//污染源因子名称
	
	private String warnitemname;//报警项目名称
	
	private float threshold;//限值
	
	private float realvalue;//真实值
	
	private float stdvalue;//标准值
	
	private String warningtime;//报警时间
	
	private String status;//报警状态
	
	private String content;//报警内容
	
	
	public WarningLog1() {
		
	}

	public WarningLog1(String fwarninglogid, String entername, String ctrname,
			String pollutantname, String warnitemname, float threshold,
			float realvalue, float stdvalue, String warningtime, String status,String content) {
		
		this.fwarninglogid = fwarninglogid;
		this.entername = entername;
		this.ctrname = ctrname;
		this.pollutantname = pollutantname;
		this.warnitemname = warnitemname;
		this.threshold = threshold;
		this.realvalue = realvalue;
		this.stdvalue = stdvalue;
		this.warningtime = warningtime;
		this.status = status;
		this.content=content;
	}

	public String getFwarninglogid() {
		return fwarninglogid;
	}

	public void setFwarninglogid(String fwarninglogid) {
		this.fwarninglogid = fwarninglogid;
	}

	public String getEntername() {
		return entername;
	}

	public void setEntername(String entername) {
		this.entername = entername;
	}

	public String getCtrname() {
		return ctrname;
	}

	public void setCtrname(String ctrname) {
		this.ctrname = ctrname;
	}

	public String getPollutantname() {
		return pollutantname;
	}

	public void setPollutantname(String pollutantname) {
		this.pollutantname = pollutantname;
	}

	public String getWarnitemname() {
		return warnitemname;
	}

	public void setWarnitemname(String warnitemname) {
		this.warnitemname = warnitemname;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public float getRealvalue() {
		return realvalue;
	}

	public void setRealvalue(float realvalue) {
		this.realvalue = realvalue;
	}

	public float getStdvalue() {
		return stdvalue;
	}

	public void setStdvalue(float stdvalue) {
		this.stdvalue = stdvalue;
	}

	public String getWarningtime() {
		return warningtime;
	}

	public void setWarningtime(String warningtime) {
		this.warningtime = warningtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
