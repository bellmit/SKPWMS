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
 * 企业类型
 * 2014-10-23
 */
@Entity
@Table(name = "T_BAS_IndustryType")
public class TBasIndustryType implements  Serializable{

	private static final long serialVersionUID = 1L;
	private String findustryTypeId;
	private String findustryTypeCode;
	private String findustryTypeName;
	private String fparentId;
	private String flongCode;
	private String forgUnitId;
	private String fcreatorId;
	private String fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FIndustryTypeID", unique = true, nullable = false, length = 32)
	public String getFindustryTypeId() {
		return this.findustryTypeId;
	}

	public void setFindustryTypeId(String findustryTypeId) {
		this.findustryTypeId = findustryTypeId;
	}

	@Column(name = "FIndustryTypeCode", length = 60)
	public String getFindustryTypeCode() {
		return this.findustryTypeCode;
	}

	public void setFindustryTypeCode(String findustryTypeCode) {
		this.findustryTypeCode = findustryTypeCode;
	}

	@Column(name = "FIndustryTypeName", length = 100)
	public String getFindustryTypeName() {
		return this.findustryTypeName;
	}

	public void setFindustryTypeName(String findustryTypeName) {
		this.findustryTypeName = findustryTypeName;
	}

	@Column(name = "FParentID")
	public String getFparentId() {
		return this.fparentId;
	}

	public void setFparentId(String fparentId) {
		this.fparentId = fparentId;
	}

	@Column(name = "FLongCode", length = 1000)
	public String getFlongCode() {
		return this.flongCode;
	}

	public void setFlongCode(String flongCode) {
		this.flongCode = flongCode;
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
	public String getFcreatTime() {
		return this.fcreatTime;
	}

	public void setFcreatTime(String fcreatTime) {
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