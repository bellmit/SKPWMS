package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasEnterManagement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_EnterManagement", schema = "dbo", catalog = "SKPW_DEV")
public class TBasEnterManagement{

	// Fields

	private String fenterId;	//	企业ID
	private String fctrlLevelId;	//	环保监管级别
	private Boolean fisStressSource;	//	是重点源
	private Boolean fisKeySewage;	//	是废水重点污染源
	private Boolean fisKeyGas;	//	是废气重点污染源
	private Boolean fisKeySolid;	//	是固废重点污染源
	private Boolean fisDanger;	//	是否风险源
	private Boolean fisSewageFactory;	//	是否污水治理处理厂
	private Boolean fisOlmonitor;	//	是否在线监测企业
	private Boolean fisOutSubmit;	//	是否排污申报
	private Boolean fisSolidManager;	//	是否固废经营单位
	private Boolean fisOutFee;	//	是否开征排污费
	private String forgUnitId;	//	区域
	private String fcreatorId;	//	创建人
	private Timestamp fcreatTime;	//	创建时间
	private String flastEditId;	//	最后修改人
	private Timestamp flastEditTime;	//	最后修改时间
	private Boolean fisDisable;	//	禁用标志
	private String fcreditGradeID;//环保信用等级ID


	// Property accessors
	@Id
	@Column(name = "FEnterID", unique = true, nullable = false, length = 32)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
	}

	@Column(name = "FCtrlLevelID", length = 32)
	public String getFctrlLevelId() {
		return this.fctrlLevelId;
	}

	public void setFctrlLevelId(String fctrlLevelId) {
		this.fctrlLevelId = fctrlLevelId;
	}

	@Column(name = "FIsStressSource")
	public Boolean getFisStressSource() {
		return this.fisStressSource;
	}

	public void setFisStressSource(Boolean fisStressSource) {
		this.fisStressSource = fisStressSource;
	}

	@Column(name = "FIsKeySewage")
	public Boolean getFisKeySewage() {
		return this.fisKeySewage;
	}

	public void setFisKeySewage(Boolean fisKeySewage) {
		this.fisKeySewage = fisKeySewage;
	}

	@Column(name = "FisKeyGas")
	public Boolean getFisKeyGas() {
		return this.fisKeyGas;
	}

	public void setFisKeyGas(Boolean fisKeyGas) {
		this.fisKeyGas = fisKeyGas;
	}

	@Column(name = "FIsKeySolid")
	public Boolean getFisKeySolid() {
		return this.fisKeySolid;
	}

	public void setFisKeySolid(Boolean fisKeySolid) {
		this.fisKeySolid = fisKeySolid;
	}

	@Column(name = "FIsDanger")
	public Boolean getFisDanger() {
		return this.fisDanger;
	}

	public void setFisDanger(Boolean fisDanger) {
		this.fisDanger = fisDanger;
	}

	@Column(name = "FIsSewageFactory")
	public Boolean getFisSewageFactory() {
		return this.fisSewageFactory;
	}

	public void setFisSewageFactory(Boolean fisSewageFactory) {
		this.fisSewageFactory = fisSewageFactory;
	}

	@Column(name = "FIsOLMonitor")
	public Boolean getFisOlmonitor() {
		return this.fisOlmonitor;
	}

	public void setFisOlmonitor(Boolean fisOlmonitor) {
		this.fisOlmonitor = fisOlmonitor;
	}

	@Column(name = "FIsOutSubmit")
	public Boolean getFisOutSubmit() {
		return this.fisOutSubmit;
	}

	public void setFisOutSubmit(Boolean fisOutSubmit) {
		this.fisOutSubmit = fisOutSubmit;
	}

	@Column(name = "FIsSolidManager")
	public Boolean getFisSolidManager() {
		return this.fisSolidManager;
	}

	public void setFisSolidManager(Boolean fisSolidManager) {
		this.fisSolidManager = fisSolidManager;
	}

	@Column(name = "FIsOutFee")
	public Boolean getFisOutFee() {
		return this.fisOutFee;
	}

	public void setFisOutFee(Boolean fisOutFee) {
		this.fisOutFee = fisOutFee;
	}

	@Column(name = "FOrgUnitID", length = 32)
	public String getForgUnitId() {
		return this.forgUnitId;
	}

	public void setForgUnitId(String forgUnitId) {
		this.forgUnitId = forgUnitId;
	}

	@Column(name = "FCreatorID", length = 32)
	public String getFcreatorId() {
		return this.fcreatorId;
	}

	public void setFcreatorId(String fcreatorId) {
		this.fcreatorId = fcreatorId;
	}

	@Column(name = "FCreatTime", length = 23)
	public Timestamp getFcreatTime() {
		return this.fcreatTime;
	}

	public void setFcreatTime(Timestamp fcreatTime) {
		this.fcreatTime = fcreatTime;
	}

	@Column(name = "FLastEditID", length = 32)
	public String getFlastEditId() {
		return this.flastEditId;
	}

	public void setFlastEditId(String flastEditId) {
		this.flastEditId = flastEditId;
	}

	@Column(name = "FLastEditTime", length = 23)
	public Timestamp getFlastEditTime() {
		return this.flastEditTime;
	}

	public void setFlastEditTime(Timestamp flastEditTime) {
		this.flastEditTime = flastEditTime;
	}

	@Column(name = "FIsDisable")
	public Boolean getFisDisable() {
		return this.fisDisable;
	}

	public void setFisDisable(Boolean fisDisable) {
		this.fisDisable = fisDisable;
	}

	@Column(name = "FCreditGradeID")
	public String getFcreditGradeID() {
		return fcreditGradeID;
	}

	public void setFcreditGradeID(String fcreditGradeID) {
		this.fcreditGradeID = fcreditGradeID;
	}

	
}