package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_BAS_Pollutant")
public class TBasPollutant extends SuperEntity implements  Serializable{
	@Id
	private String fPollutantID; // 污染物ID char

	@Column(name = "FID")
	private Integer fID; // ID int

	// private String fPollTypeID; //污染物类别ID char
	@ManyToOne
	@JoinColumn(name = "FPollTypeID")
	private TBasPollutantType tBasPollutantType;

	private String fPollutantCode; // 污染物编号 varchar

	private String fPollutantName; // 污染物名称 varchar

	private String fPollSimName; // 简称 varchar

	private String fUnit; // 单位 varchar

	private Long fEquValue; // 当量值 int

	private String fEquUnit; // 当量值单位 varchar

	private Boolean fIsMonitor; // 是否有监测 bit

	private Boolean fIsNeedAnalyse; // 是否需要分析 bit

	private Boolean fIsAnalyse; // 是否直接分析 bit

	public String getfPollutantID() {
		return fPollutantID;
	}

	public void setfPollutantID(String fPollutantID) {
		this.fPollutantID = fPollutantID;
	}

	public Integer getfID() {
		return fID;
	}

	public void setfID(Integer fID) {
		this.fID = fID;
	}

	public TBasPollutantType gettBasPollutantType() {
		return tBasPollutantType;
	}

	public void settBasPollutantType(TBasPollutantType tBasPollutantType) {
		this.tBasPollutantType = tBasPollutantType;
	}

	public String getfPollutantCode() {
		return fPollutantCode;
	}

	public void setfPollutantCode(String fPollutantCode) {
		this.fPollutantCode = fPollutantCode;
	}

	public String getfPollutantName() {
		return fPollutantName;
	}

	public void setfPollutantName(String fPollutantName) {
		this.fPollutantName = fPollutantName;
	}

	public String getfPollSimName() {
		return fPollSimName;
	}

	public void setfPollSimName(String fPollSimName) {
		this.fPollSimName = fPollSimName;
	}

	public String getfUnit() {
		return fUnit;
	}

	public void setfUnit(String fUnit) {
		this.fUnit = fUnit;
	}

	public Long getfEquValue() {
		return fEquValue;
	}

	public void setfEquValue(Long fEquValue) {
		this.fEquValue = fEquValue;
	}

	public String getfEquUnit() {
		return fEquUnit;
	}

	public void setfEquUnit(String fEquUnit) {
		this.fEquUnit = fEquUnit;
	}

	public Boolean getfIsMonitor() {
		return fIsMonitor;
	}

	public void setfIsMonitor(Boolean fIsMonitor) {
		this.fIsMonitor = fIsMonitor;
	}

	public Boolean getfIsNeedAnalyse() {
		return fIsNeedAnalyse;
	}

	public void setfIsNeedAnalyse(Boolean fIsNeedAnalyse) {
		this.fIsNeedAnalyse = fIsNeedAnalyse;
	}

	public Boolean getfIsAnalyse() {
		return fIsAnalyse;
	}

	public void setfIsAnalyse(Boolean fIsAnalyse) {
		this.fIsAnalyse = fIsAnalyse;
	}

}
