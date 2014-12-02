package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "T_BAS_RegisterType")
public class TBasRegisterType implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	private String fregisterTypeId;
	private String fregisterTypeCode;
	private String fregisterTypeName;
	private String forgUnitId;
	private String fcreatorId;
	private String fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FRegisterTypeID", unique = true, nullable = false, length = 32)
	public String getFregisterTypeId() {
		return this.fregisterTypeId;
	}

	public void setFregisterTypeId(String fregisterTypeId) {
		this.fregisterTypeId = fregisterTypeId;
	}

	@Column(name = "FRegisterTypeCode", length = 60)
	public String getFregisterTypeCode() {
		return this.fregisterTypeCode;
	}

	public void setFregisterTypeCode(String fregisterTypeCode) {
		this.fregisterTypeCode = fregisterTypeCode;
	}

	@Column(name = "FRegisterTypeName", length = 100)
	public String getFregisterTypeName() {
		return this.fregisterTypeName;
	}

	public void setFregisterTypeName(String fregisterTypeName) {
		this.fregisterTypeName = fregisterTypeName;
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