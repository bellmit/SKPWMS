package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author hjy
 * 村实体
 * 2014-10-23
 */
@Entity
@Table(name = "T_BAS_Village")
public class TBasVillage implements  Serializable{

	private static final long serialVersionUID = 1L;
	private String fvillageId;
	private String ftownId;
	private String fvillageCode;
	private String fvillageName;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@Column(name = "FVillageID", unique = true, nullable = false, length = 32)
	public String getFvillageId() {
		return this.fvillageId;
	}

	public void setFvillageId(String fvillageId) {
		this.fvillageId = fvillageId;
	}

	@Column(name = "FTownID", length = 32)
	public String getFtownId() {
		return this.ftownId;
	}

	public void setFtownId(String ftownId) {
		this.ftownId = ftownId;
	}

	@Column(name = "FVillageCode", length = 60)
	public String getFvillageCode() {
		return this.fvillageCode;
	}

	public void setFvillageCode(String fvillageCode) {
		this.fvillageCode = fvillageCode;
	}

	@Column(name = "FVillageName", length = 100)
	public String getFvillageName() {
		return this.fvillageName;
	}

	public void setFvillageName(String fvillageName) {
		this.fvillageName = fvillageName;
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