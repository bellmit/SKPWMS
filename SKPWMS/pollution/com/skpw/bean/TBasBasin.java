package com.skpw.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasBasin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_Basin", schema = "dbo", catalog = "SKPW_DEV")
public class TBasBasin {


	private String fbasinId;
	private String fbasinCode;
	private String fbasinName;
	private String fparentId;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@Column(name = "FBasinID", unique = true, nullable = false, length = 32)
	public String getFbasinId() {
		return this.fbasinId;
	}

	public void setFbasinId(String fbasinId) {
		this.fbasinId = fbasinId;
	}

	@Column(name = "FBasinCode", length = 60)
	public String getFbasinCode() {
		return this.fbasinCode;
	}

	public void setFbasinCode(String fbasinCode) {
		this.fbasinCode = fbasinCode;
	}

	@Column(name = "FBasinName", length = 100)
	public String getFbasinName() {
		return this.fbasinName;
	}

	public void setFbasinName(String fbasinName) {
		this.fbasinName = fbasinName;
	}

	@Column(name = "FParentID", length = 32)
	public String getFparentId() {
		return this.fparentId;
	}

	public void setFparentId(String fparentId) {
		this.fparentId = fparentId;
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