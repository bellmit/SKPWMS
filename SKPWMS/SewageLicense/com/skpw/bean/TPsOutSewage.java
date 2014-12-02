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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="T_PS_OutSewage")
public class TPsOutSewage extends SuperEntity{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fOutSewageID;	//废水排污口ID	char

//	private String fOutPID;	//许可证ID	char
	@ManyToOne
	@JoinColumn(name="FOutPID")
	private TPsOutPermit tPsOutPermit;

//	private String fEnterID;	//企业ID	char
	@ManyToOne
	@JoinColumn(name="FEnterID")
	private TBasEnterprise tBasEnterprise;

	private String fOutSewageCode;	//排污口编号	varchar

	private String fOutSewageName;	//排污口名称	varchar

//	private String fOutWhereID;	//排放去向ID	varchar
	@ManyToOne
	@JoinColumn(name="FOutWhereID")
	private TBasOutWhere tBasOutWhere;

//	private String fOutStandID;	//排放标准ID	varchar
	@ManyToOne
	@JoinColumn(name="FOutStandID")
	private TBasWaterDisStd tBasWaterDisStd;
	
	@OneToMany(mappedBy="tPsOutSewage",cascade=CascadeType.REMOVE)
	private List<TPsOutSPoll> tPsOutSPolls;
	
	@OneToMany(mappedBy="tPsOutSewage",cascade=CascadeType.REMOVE)
	private List<TPsOutSPollYear> tPsOutSPollYears;

	private String fSewageTypeID;	//废水种类	varchar

	private double fMaxOut;	//年度废水排放量限值	decimal

	private double fDayMaxOut;	//日废水排放量限值	decimal

	private long fStatus;	//状态	smallint

	private String fWaterDisRuleID;	//排放规律ID	char

	public String getfOutSewageID() {
		return fOutSewageID;
	}

	public void setfOutSewageID(String fOutSewageID) {
		this.fOutSewageID = fOutSewageID;
	}

	public String getfOutSewageCode() {
		return fOutSewageCode;
	}

	public void setfOutSewageCode(String fOutSewageCode) {
		this.fOutSewageCode = fOutSewageCode;
	}

	public String getfOutSewageName() {
		return fOutSewageName;
	}

	public void setfOutSewageName(String fOutSewageName) {
		this.fOutSewageName = fOutSewageName;
	}

	public String getfSewageTypeID() {
		return fSewageTypeID;
	}

	public void setfSewageTypeID(String fSewageTypeID) {
		this.fSewageTypeID = fSewageTypeID;
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

	public long getfStatus() {
		return fStatus;
	}

	public void setfStatus(long fStatus) {
		this.fStatus = fStatus;
	}

	public String getfWaterDisRuleID() {
		return fWaterDisRuleID;
	}

	public void setfWaterDisRuleID(String fWaterDisRuleID) {
		this.fWaterDisRuleID = fWaterDisRuleID;
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

	public TBasOutWhere gettBasOutWhere() {
		return tBasOutWhere;
	}

	public void settBasOutWhere(TBasOutWhere tBasOutWhere) {
		this.tBasOutWhere = tBasOutWhere;
	}

	public TBasWaterDisStd gettBasWaterDisStd() {
		return tBasWaterDisStd;
	}

	public void settBasWaterDisStd(TBasWaterDisStd tBasWaterDisStd) {
		this.tBasWaterDisStd = tBasWaterDisStd;
	}

	@JsonIgnore 
	public List<TPsOutSPoll> gettPsOutSPolls() {
		return tPsOutSPolls;
	}

	public void settPsOutSPolls(List<TPsOutSPoll> tPsOutSPolls) {
		this.tPsOutSPolls = tPsOutSPolls;
	}

	@JsonIgnore 
	public List<TPsOutSPollYear> gettPsOutSPollYears() {
		return tPsOutSPollYears;
	}

	public void settPsOutSPollYears(List<TPsOutSPollYear> tPsOutSPollYears) {
		this.tPsOutSPollYears = tPsOutSPollYears;
	}


}
