package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="T_PS_OutSPollYear")
public class TPsOutSPollYear extends SuperEntity{
	@Id
	private String fOutSPollYearID;	//废水污染物年度排放限值ID	varchar

	private Long fYearID;	//年ID	int

//	private String fOutSewageID;	//废水排放口ID	varchar
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

	private Double fDischarge;	//排放限值	decimal

	public String getfOutSPollYearID() {
		return fOutSPollYearID;
	}

	public void setfOutSPollYearID(String fOutSPollYearID) {
		this.fOutSPollYearID = fOutSPollYearID;
	}

	public Long getfYearID() {
		return fYearID;
	}

	public void setfYearID(Long fYearID) {
		this.fYearID = fYearID;
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

	public Double getfDischarge() {
		return fDischarge;
	}

	public void setfDischarge(Double fDischarge) {
		this.fDischarge = fDischarge;
	}


}
