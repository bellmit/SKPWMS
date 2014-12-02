package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasEnterEnviron entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_EnterEnviron", schema = "dbo", catalog = "SKPW_DEV")
public class TBasEnterEnviron{

	// Fields

	private String fenterId;	//	
	private String foneLevBasinId;	//	一级流域
	private String ftwoLevBasinId;	//	二级流域
	private String fthreeBasinId;	//	三级流域
	private Boolean fisWaterArea;	//	是否水源区
	private String fwaterAreaId;	//	水域功能区级别
	private String fnoiseAreaId;	//	噪声功能区级别
	private String fgasAreaId;	//	空气功能区级别
	private Boolean fiso2ctrlArea;	//	是否SO2控制区
	private Boolean fisAcidCtrlArea;	//	是否酸雨控制区
	private String forgUnitId;	//	组织id
	private String fcreatorId;	//	创建人
	private Timestamp fcreatTime;	//	创建时间
	private String flastEditId;	//	最后修改人
	private Timestamp flastEditTime;	//	最后修改时间
	private Boolean fisDisable;	//	禁用标志

	// Constructors


	// Property accessors
	@Id
	@Column(name = "FEnterID", unique = true, nullable = false, length = 32)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
	}

	@Column(name = "FOneLevBasinID", length = 32)
	public String getFoneLevBasinId() {
		return this.foneLevBasinId;
	}

	public void setFoneLevBasinId(String foneLevBasinId) {
		this.foneLevBasinId = foneLevBasinId;
	}

	@Column(name = "FTwoLevBasinID", length = 32)
	public String getFtwoLevBasinId() {
		return this.ftwoLevBasinId;
	}

	public void setFtwoLevBasinId(String ftwoLevBasinId) {
		this.ftwoLevBasinId = ftwoLevBasinId;
	}

	@Column(name = "FThreeBasinID", length = 32)
	public String getFthreeBasinId() {
		return this.fthreeBasinId;
	}

	public void setFthreeBasinId(String fthreeBasinId) {
		this.fthreeBasinId = fthreeBasinId;
	}

	@Column(name = "FIsWaterArea")
	public Boolean getFisWaterArea() {
		return this.fisWaterArea;
	}

	public void setFisWaterArea(Boolean fisWaterArea) {
		this.fisWaterArea = fisWaterArea;
	}

	@Column(name = "FWaterAreaID", length = 32)
	public String getFwaterAreaId() {
		return this.fwaterAreaId;
	}

	public void setFwaterAreaId(String fwaterAreaId) {
		this.fwaterAreaId = fwaterAreaId;
	}

	@Column(name = "FNoiseAreaID", length = 32)
	public String getFnoiseAreaId() {
		return this.fnoiseAreaId;
	}

	public void setFnoiseAreaId(String fnoiseAreaId) {
		this.fnoiseAreaId = fnoiseAreaId;
	}

	@Column(name = "FGasAreaID", length = 32)
	public String getFgasAreaId() {
		return this.fgasAreaId;
	}

	public void setFgasAreaId(String fgasAreaId) {
		this.fgasAreaId = fgasAreaId;
	}

	@Column(name = "FISO2CtrlArea")
	public Boolean getFiso2ctrlArea() {
		return this.fiso2ctrlArea;
	}

	public void setFiso2ctrlArea(Boolean fiso2ctrlArea) {
		this.fiso2ctrlArea = fiso2ctrlArea;
	}

	@Column(name = "FIsAcidCtrlArea")
	public Boolean getFisAcidCtrlArea() {
		return this.fisAcidCtrlArea;
	}

	public void setFisAcidCtrlArea(Boolean fisAcidCtrlArea) {
		this.fisAcidCtrlArea = fisAcidCtrlArea;
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

}