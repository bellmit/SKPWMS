package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_PS_Permits")
public class TPsPermits {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fPermitsID;	//企业年度排放量ID	char

//	private String fEnterID;	//企业ID	char
	@ManyToOne
	@JoinColumn(name="FEnterID")
	private TBasEnterprise tBasEnterprise;

//	private String fPollutantID;	//污染因子ID	char
	@ManyToOne
	@JoinColumn(name="FPollutantID")
	private TBasPollutant tBasPollutant;

	private Long fYearID;	//年度ID	int

	private Double fInitPermitValue;	//初始许可排放量	decimal

	private Double fAddPermit;	//累计增加排放量	decimal

	private Double fPermitValue;	//实际排放量	decimal

	public String getfPermitsID() {
		return fPermitsID;
	}

	public void setfPermitsID(String fPermitsID) {
		this.fPermitsID = fPermitsID;
	}

	public TBasEnterprise gettBasEnterprise() {
		return tBasEnterprise;
	}

	public void settBasEnterprise(TBasEnterprise tBasEnterprise) {
		this.tBasEnterprise = tBasEnterprise;
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

	public Double getfInitPermitValue() {
		return fInitPermitValue;
	}

	public void setfInitPermitValue(Double fInitPermitValue) {
		this.fInitPermitValue = fInitPermitValue;
	}

	public Double getfAddPermit() {
		return fAddPermit;
	}

	public void setfAddPermit(Double fAddPermit) {
		this.fAddPermit = fAddPermit;
	}

	public Double getfPermitValue() {
		return fPermitValue;
	}

	public void setfPermitValue(Double fPermitValue) {
		this.fPermitValue = fPermitValue;
	}


}
