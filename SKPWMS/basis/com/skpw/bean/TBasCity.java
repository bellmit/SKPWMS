package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * @author hjy
 * 市级行政单位
 * 2014-10-23
 */
@Entity
@Table(name = "T_BAS_City")
public class TBasCity implements  Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FCityID", unique = true, nullable = false, length = 32)
	private String fcityId;
	
	@Column(name = "FProvinceID", length = 32)
	private String fprovinceId;
	
	@Column(name = "FCityCode", length = 60)
	private String fcityCode;
	
	@Column(name = "FCityName", length = 100)
	private String fcityName;
	
	@Column(name = "FOrgUnitID", length = 32)
	private String forgUnitId;
	
	@Column(name = "FCreatorID", length = 32)
	private String fcreatorId;
	
	@Column(name = "FCreatTime", length = 23)
	private Timestamp fcreatTime;
	
	@Column(name = "FLastEditID", length = 32)
	private String flastEditId;
	
	@Column(name = "FLastEditTime", length = 23)
	private Timestamp flastEditTime;
	
	@Column(name = "FIsDisable")
	private Boolean fisDisable;

	
	
	public String getFcityId() {
		return fcityId;
	}

	public void setFcityId(String fcityId) {
		this.fcityId = fcityId;
	}

	public String getFprovinceId() {
		return fprovinceId;
	}

	public void setFprovinceId(String fprovinceId) {
		this.fprovinceId = fprovinceId;
	}

	public String getFcityCode() {
		return fcityCode;
	}

	public void setFcityCode(String fcityCode) {
		this.fcityCode = fcityCode;
	}

	public String getFcityName() {
		return fcityName;
	}

	public void setFcityName(String fcityName) {
		this.fcityName = fcityName;
	}

	public String getForgUnitId() {
		return forgUnitId;
	}

	public void setForgUnitId(String forgUnitId) {
		this.forgUnitId = forgUnitId;
	}

	public String getFcreatorId() {
		return fcreatorId;
	}

	public void setFcreatorId(String fcreatorId) {
		this.fcreatorId = fcreatorId;
	}

	public Timestamp getFcreatTime() {
		return fcreatTime;
	}

	public void setFcreatTime(Timestamp fcreatTime) {
		this.fcreatTime = fcreatTime;
	}

	public String getFlastEditId() {
		return flastEditId;
	}

	public void setFlastEditId(String flastEditId) {
		this.flastEditId = flastEditId;
	}

	public Timestamp getFlastEditTime() {
		return flastEditTime;
	}

	public void setFlastEditTime(Timestamp flastEditTime) {
		this.flastEditTime = flastEditTime;
	}

	public Boolean getFisDisable() {
		return fisDisable;
	}

	public void setFisDisable(Boolean fisDisable) {
		this.fisDisable = fisDisable;
	}


}