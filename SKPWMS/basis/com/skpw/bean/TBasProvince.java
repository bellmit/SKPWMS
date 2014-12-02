package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "T_BAS_Province")
public class TBasProvince implements  Serializable{

	// Fields

	private String fprovinceId;
	private String fprovinceCode;
	private String fprovinceName;
	
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;

	@Id
	@Column(name = "FProvinceID", unique = true, nullable = false, length = 32)
	public String getFprovinceId() {
		return this.fprovinceId;
	}

	public void setFprovinceId(String fprovinceId) {
		this.fprovinceId = fprovinceId;
	}

	@Column(name = "FProvinceCode", length = 60)
	public String getFprovinceCode() {
		return this.fprovinceCode;
	}

	public void setFprovinceCode(String fprovinceCode) {
		this.fprovinceCode = fprovinceCode;
	}

	@Column(name = "FProvinceName", length = 100)
	public String getFprovinceName() {
		return this.fprovinceName;
	}

	public void setFprovinceName(String fprovinceName) {
		this.fprovinceName = fprovinceName;
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