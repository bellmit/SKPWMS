package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasCtrlLevelId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_CtrlLevel", schema = "dbo", catalog = "SKPW_DEV")
public class TBasCtrlLevel{


	private String fctrlLevelId;
	private String fctrlLevelCode;
	private String fctrlLevelName;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@Column(name = "FCtrlLevelID", length = 32)
	public String getFctrlLevelId() {
		return this.fctrlLevelId;
	}

	public void setFctrlLevelId(String fctrlLevelId) {
		this.fctrlLevelId = fctrlLevelId;
	}

	@Column(name = "FCtrlLevelCode", length = 40)
	public String getFctrlLevelCode() {
		return this.fctrlLevelCode;
	}

	public void setFctrlLevelCode(String fctrlLevelCode) {
		this.fctrlLevelCode = fctrlLevelCode;
	}

	@Column(name = "FCtrlLevelName", length = 100)
	public String getFctrlLevelName() {
		return this.fctrlLevelName;
	}

	public void setFctrlLevelName(String fctrlLevelName) {
		this.fctrlLevelName = fctrlLevelName;
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