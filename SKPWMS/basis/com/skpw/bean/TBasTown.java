package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author hjy
 * 镇实体
 * 2014-10-23
 */
@Entity
@Table(name = "T_BAS_Town")
public class TBasTown implements  Serializable{

	private static final long serialVersionUID = 1L;
	private String ftownId;
	private String fcountyId;
	private String ftownCode;
	private String ftownName;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@Column(name = "FTownID", unique = true, nullable = false, length = 32)
	public String getFtownId() {
		return this.ftownId;
	}

	public void setFtownId(String ftownId) {
		this.ftownId = ftownId;
	}

	@Column(name = "FCountyID", length = 32)
	public String getFcountyId() {
		return this.fcountyId;
	}

	public void setFcountyId(String fcountyId) {
		this.fcountyId = fcountyId;
	}

	@Column(name = "FTownCode", length = 60)
	public String getFtownCode() {
		return this.ftownCode;
	}

	public void setFtownCode(String ftownCode) {
		this.ftownCode = ftownCode;
	}

	@Column(name = "FTownName", length = 100)
	public String getFtownName() {
		return this.ftownName;
	}

	public void setFtownName(String ftownName) {
		this.ftownName = ftownName;
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