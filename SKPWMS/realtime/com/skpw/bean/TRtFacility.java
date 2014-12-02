package com.skpw.bean;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRtFacility entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RT_Facility")
public class TRtFacility implements java.io.Serializable {

	// Fields

	private Integer ffacilityId;
	private String ffacilityNo;
	private String ffacilityName;
	private String fenterId;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;
	private String fversion;

	// Constructors

	/** default constructor */
	public TRtFacility() {
	}

	/** minimal constructor */
	public TRtFacility(Integer ffacilityId, String fversion) {
		this.ffacilityId = ffacilityId;
		this.fversion = fversion;
	}

	/** full constructor */
	public TRtFacility(Integer ffacilityId, String ffacilityNo,
			String ffacilityName, String fenterId, String forgUnitId,
			String fcreatorId, Timestamp fcreatTime, String flastEditId,
			Timestamp flastEditTime, Boolean fisDisable, String fversion) {
		this.ffacilityId = ffacilityId;
		this.ffacilityNo = ffacilityNo;
		this.ffacilityName = ffacilityName;
		this.fenterId = fenterId;
		this.forgUnitId = forgUnitId;
		this.fcreatorId = fcreatorId;
		this.fcreatTime = fcreatTime;
		this.flastEditId = flastEditId;
		this.flastEditTime = flastEditTime;
		this.fisDisable = fisDisable;
		this.fversion = fversion;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "FFacilityID", unique = true, nullable = false)
	public Integer getFfacilityId() {
		return this.ffacilityId;
	}

	public void setFfacilityId(Integer ffacilityId) {
		this.ffacilityId = ffacilityId;
	}

	@Column(name = "FFacilityNo", length = 40)
	public String getFfacilityNo() {
		return this.ffacilityNo;
	}

	public void setFfacilityNo(String ffacilityNo) {
		this.ffacilityNo = ffacilityNo;
	}

	@Column(name = "FFacilityName", length = 100)
	public String getFfacilityName() {
		return this.ffacilityName;
	}

	public void setFfacilityName(String ffacilityName) {
		this.ffacilityName = ffacilityName;
	}

	@Column(name = "FEnterID", length = 100)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
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

	@Column(name = "FVersion", nullable = false, insertable = false, updatable = false)
	public String getFversion() {
		return this.fversion;
	}

	public void setFversion(String fversion) {
		this.fversion = fversion;
	}

}