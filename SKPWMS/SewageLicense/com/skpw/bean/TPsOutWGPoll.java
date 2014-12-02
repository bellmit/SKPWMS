package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_PS_OutWGPoll")
public class TPsOutWGPoll {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fOutWGPollID;	//废气主要污染物ID	varchar

	//private String fWGOutletID;	//废气排放口ID	varchar
	@ManyToOne
	@JoinColumn(name="FWGOutletID")
	private TPsWasteGasOutlet tPsWasteGasOutlet;

	//private String fOutPID;	//许可证ID	varchar

	@ManyToOne
	@JoinColumn(name="FOutPID")
	private TPsOutPermit tPsOutPermit;
	
	//private String fPollutantID;	//污染物ID	varchar
	@ManyToOne
	@JoinColumn(name="fPollutantID")
	private TBasPollutant tBasPollutant;

	private double fLowerLimit;	//排放浓度下限	decimal

	private double fUpperLimit;	//排放浓度上限	decimal

	private String fOutStandID;	//排放标准ID	varchar

	private double fOutStandValue;	//排放标准值	decimal

	private double fDayMaxOut;	//日最大排放量	decimal

	private double fMaxOut;	//年最大排放量	decimal

	private double fQuarterOut;	//季度最大排放量	decimal

	private double fMonthOut;	//月度最大排放量	decimal

	public String getfOutWGPollID() {
		return fOutWGPollID;
	}

	public void setfOutWGPollID(String fOutWGPollID) {
		this.fOutWGPollID = fOutWGPollID;
	}

	public double getfLowerLimit() {
		return fLowerLimit;
	}

	public void setfLowerLimit(double fLowerLimit) {
		this.fLowerLimit = fLowerLimit;
	}

	public double getfUpperLimit() {
		return fUpperLimit;
	}

	public void setfUpperLimit(double fUpperLimit) {
		this.fUpperLimit = fUpperLimit;
	}

	public String getfOutStandID() {
		return fOutStandID;
	}

	public void setfOutStandID(String fOutStandID) {
		this.fOutStandID = fOutStandID;
	}

	public double getfOutStandValue() {
		return fOutStandValue;
	}

	public void setfOutStandValue(double fOutStandValue) {
		this.fOutStandValue = fOutStandValue;
	}

	public double getfDayMaxOut() {
		return fDayMaxOut;
	}

	public void setfDayMaxOut(double fDayMaxOut) {
		this.fDayMaxOut = fDayMaxOut;
	}

	public double getfMaxOut() {
		return fMaxOut;
	}

	public void setfMaxOut(double fMaxOut) {
		this.fMaxOut = fMaxOut;
	}

	public double getfQuarterOut() {
		return fQuarterOut;
	}

	public void setfQuarterOut(double fQuarterOut) {
		this.fQuarterOut = fQuarterOut;
	}

	public double getfMonthOut() {
		return fMonthOut;
	}

	public void setfMonthOut(double fMonthOut) {
		this.fMonthOut = fMonthOut;
	}

	public TPsWasteGasOutlet gettPsWasteGasOutlet() {
		return tPsWasteGasOutlet;
	}

	public void settPsWasteGasOutlet(TPsWasteGasOutlet tPsWasteGasOutlet) {
		this.tPsWasteGasOutlet = tPsWasteGasOutlet;
	}

	public TBasPollutant gettBasPollutant() {
		return tBasPollutant;
	}

	public void settBasPollutant(TBasPollutant tBasPollutant) {
		this.tBasPollutant = tBasPollutant;
	}

	public TPsOutPermit gettPsOutPermit() {
		return tPsOutPermit;
	}

	public void settPsOutPermit(TPsOutPermit tPsOutPermit) {
		this.tPsOutPermit = tPsOutPermit;
	}


}
