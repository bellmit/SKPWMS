package com.skpw.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_PS_WasteGasOutlet")
public class TPsWasteGasOutlet {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fWGOutletID;	//废气排放口ID	varchar

	//private String fOutPID;	//许可证ID	varchar
	
	@ManyToOne
	@JoinColumn(name="FOutPID")
	private TPsOutPermit tPsOutPermit;

	//private String fEnterpriseID;	//企业ID	varchar
	@ManyToOne
	@JoinColumn(name="FEnterpriseID")
	private TBasEnterprise tBasEnterprise;

	private String fWGOutletCode;	//排放口编号	varchar

	private String fWGoutletName;	//排放口名称	varchar

	//private String fOutStandID;	//排放标准ID	varchar
	@ManyToOne
	@JoinColumn(name="FOutStandID")
	private TBasWaterDisStd tBasWaterDisStd;

	private double fMaxOut;	//年度废气排放量限值	decimal

	private double fDayMaxOut;	//日废气排放量限值	decimal

	public String getfWGOutletID() {
		return fWGOutletID;
	}

	public void setfWGOutletID(String fWGOutletID) {
		this.fWGOutletID = fWGOutletID;
	}

	public String getfWGOutletCode() {
		return fWGOutletCode;
	}

	public void setfWGOutletCode(String fWGOutletCode) {
		this.fWGOutletCode = fWGOutletCode;
	}

	public String getfWGoutletName() {
		return fWGoutletName;
	}

	public void setfWGoutletName(String fWGoutletName) {
		this.fWGoutletName = fWGoutletName;
	}

	public double getfMaxOut() {
		return fMaxOut;
	}

	public void setfMaxOut(double fMaxOut) {
		this.fMaxOut = fMaxOut;
	}

	public double getfDayMaxOut() {
		return fDayMaxOut;
	}

	public void setfDayMaxOut(double fDayMaxOut) {
		this.fDayMaxOut = fDayMaxOut;
	}

	public TPsOutPermit gettPsOutPermit() {
		return tPsOutPermit;
	}

	public void settPsOutPermit(TPsOutPermit tPsOutPermit) {
		this.tPsOutPermit = tPsOutPermit;
	}

	public TBasEnterprise gettBasEnterprise() {
		return tBasEnterprise;
	}

	public void settBasEnterprise(TBasEnterprise tBasEnterprise) {
		this.tBasEnterprise = tBasEnterprise;
	}

	public TBasWaterDisStd gettBasWaterDisStd() {
		return tBasWaterDisStd;
	}

	public void settBasWaterDisStd(TBasWaterDisStd tBasWaterDisStd) {
		this.tBasWaterDisStd = tBasWaterDisStd;
	}



}
