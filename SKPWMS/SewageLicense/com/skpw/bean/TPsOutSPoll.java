package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="T_PS_OutSPoll")
public class TPsOutSPoll extends SuperEntity{
	@Id
	private String fOutSPollID;	//废水主要污染物子表ID	varchar

//	private String fOutSewageID;	//废水排口ID	varchar
	@ManyToOne
	@JoinColumn(name="fOutSewageID")
	private TPsOutSewage tPsOutSewage;

//	private String fOutPID;	//许可证ID	varchar
	@ManyToOne
	@JoinColumn(name="FOutPID")
	private TPsOutPermit tPsOutPermit;

//	private String fPollutantID;	//污染物ID	varchar
	@ManyToOne
	@JoinColumn(name="fPollutantID")
	private TBasPollutant tBasPollutant;

	private Double fLowerLimit;	//排放浓度下限	decimal

	private Double fUpperLimit;	//排放浓度上限	decimal

	private Double fOutStandID;	//排放标准ID	decimal

	private Double fOutStandValue;	//排放标准值	decimal

	private Double fDayMaxOut;	//日最大排放量	decimal

	private Double fMaxOut;	//年最大排放量	decimal

	private Double fQuarterOut;	//季度最大排放量	decimal

	private Double fMonthOut;	//月度最大排放量	decimal

	public String getfOutSPollID() {
		return fOutSPollID;
	}

	public void setfOutSPollID(String fOutSPollID) {
		this.fOutSPollID = fOutSPollID;
	}

	public TPsOutSewage gettPsOutSewage() {
		return tPsOutSewage;
	}

	public void settPsOutSewage(TPsOutSewage tPsOutSewage) {
		this.tPsOutSewage = tPsOutSewage;
	}

	public TPsOutPermit gettPsOutPermit() {
		return tPsOutPermit;
	}

	public void settPsOutPermit(TPsOutPermit tPsOutPermit) {
		this.tPsOutPermit = tPsOutPermit;
	}

	public TBasPollutant gettBasPollutant() {
		return tBasPollutant;
	}

	public void settBasPollutant(TBasPollutant tBasPollutant) {
		this.tBasPollutant = tBasPollutant;
	}

	public Double getfLowerLimit() {
		return fLowerLimit;
	}

	public void setfLowerLimit(Double fLowerLimit) {
		this.fLowerLimit = fLowerLimit;
	}

	public Double getfUpperLimit() {
		return fUpperLimit;
	}

	public void setfUpperLimit(Double fUpperLimit) {
		this.fUpperLimit = fUpperLimit;
	}

	public Double getfOutStandID() {
		return fOutStandID;
	}

	public void setfOutStandID(Double fOutStandID) {
		this.fOutStandID = fOutStandID;
	}

	public Double getfOutStandValue() {
		return fOutStandValue;
	}

	public void setfOutStandValue(Double fOutStandValue) {
		this.fOutStandValue = fOutStandValue;
	}

	public Double getfDayMaxOut() {
		return fDayMaxOut;
	}

	public void setfDayMaxOut(Double fDayMaxOut) {
		this.fDayMaxOut = fDayMaxOut;
	}

	public Double getfMaxOut() {
		return fMaxOut;
	}

	public void setfMaxOut(Double fMaxOut) {
		this.fMaxOut = fMaxOut;
	}

	public Double getfQuarterOut() {
		return fQuarterOut;
	}

	public void setfQuarterOut(Double fQuarterOut) {
		this.fQuarterOut = fQuarterOut;
	}

	public Double getfMonthOut() {
		return fMonthOut;
	}

	public void setfMonthOut(Double fMonthOut) {
		this.fMonthOut = fMonthOut;
	}


}
