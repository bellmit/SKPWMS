package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="T_PS_OutWGPollYear")
public class TPsOutWGPollYear {
	@Id
	private String fOutWGPollYearID;	//废气污染物年度排放限值ID	varchar
	
	private Long fYearID;	//年ID

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

	private double fDischarge;	//排放限值	decimal

	public String getfOutWGPollYearID() {
		return fOutWGPollYearID;
	}

	public void setfOutWGPollYearID(String fOutWGPollYearID) {
		this.fOutWGPollYearID = fOutWGPollYearID;
	}

	public double getfDischarge() {
		return fDischarge;
	}

	public void setfDischarge(double fDischarge) {
		this.fDischarge = fDischarge;
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

	public Long getfYearID() {
		return fYearID;
	}

	public void setfYearID(Long fYearID) {
		this.fYearID = fYearID;
	}

	public TPsOutPermit gettPsOutPermit() {
		return tPsOutPermit;
	}

	public void settPsOutPermit(TPsOutPermit tPsOutPermit) {
		this.tPsOutPermit = tPsOutPermit;
	}


}
