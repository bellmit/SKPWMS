package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TPsOutSewage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PS_OutSewage", schema = "dbo", catalog = "SKPW_DEV")
public class TPsOutSewagebak implements java.io.Serializable {

	// Fields

	private String foutSewageId;
	private String foutPid;
	private String fenterId;
	private String foutSewageCode;
	private String foutSewageName;
	private String foutWhereId;
	private String foutStandId;
	private String fsewageTypeId;
	private Double fmaxOut;
	private Double fdayMaxOut;
	private Short fstatus;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	// Property accessors
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FOutSewageID", unique = true, nullable = false, length = 32)
	public String getFoutSewageId() {
		return this.foutSewageId;
	}

	public void setFoutSewageId(String foutSewageId) {
		this.foutSewageId = foutSewageId;
	}

	@Column(name = "FOutPID", length = 32)
	public String getFoutPid() {
		return this.foutPid;
	}

	public void setFoutPid(String foutPid) {
		this.foutPid = foutPid;
	}

	@Column(name = "FEnterID", length = 32)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
	}

	@Column(name = "FOutSewageCode", length = 60)
	public String getFoutSewageCode() {
		return this.foutSewageCode;
	}

	public void setFoutSewageCode(String foutSewageCode) {
		this.foutSewageCode = foutSewageCode;
	}

	@Column(name = "FOutSewageName", length = 100)
	public String getFoutSewageName() {
		return this.foutSewageName;
	}

	public void setFoutSewageName(String foutSewageName) {
		this.foutSewageName = foutSewageName;
	}

	@Column(name = "FOutWhereID", length = 32)
	public String getFoutWhereId() {
		return this.foutWhereId;
	}

	public void setFoutWhereId(String foutWhereId) {
		this.foutWhereId = foutWhereId;
	}

	@Column(name = "FOutStandID", length = 32)
	public String getFoutStandId() {
		return this.foutStandId;
	}

	public void setFoutStandId(String foutStandId) {
		this.foutStandId = foutStandId;
	}

	@Column(name = "FSewageTypeID", length = 32)
	public String getFsewageTypeId() {
		return this.fsewageTypeId;
	}

	public void setFsewageTypeId(String fsewageTypeId) {
		this.fsewageTypeId = fsewageTypeId;
	}

	@Column(name = "FMaxOut")
	public Double getFmaxOut() {
		return this.fmaxOut;
	}

	public void setFmaxOut(Double fmaxOut) {
		this.fmaxOut = fmaxOut;
	}

	@Column(name = "FDayMaxOut")
	public Double getFdayMaxOut() {
		return this.fdayMaxOut;
	}

	public void setFdayMaxOut(Double fdayMaxOut) {
		this.fdayMaxOut = fdayMaxOut;
	}

	@Column(name = "FStatus")
	public Short getFstatus() {
		return this.fstatus;
	}

	public void setFstatus(Short fstatus) {
		this.fstatus = fstatus;
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