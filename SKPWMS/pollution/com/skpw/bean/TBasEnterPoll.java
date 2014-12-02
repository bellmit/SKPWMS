package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TBasEnterPollId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_EnterPoll", schema = "dbo", catalog = "SKPW_DEV")
public class TBasEnterPoll {

	private String fenterPollId;
	private String fenterId;
	private String fpollutantId;
	private Boolean bisChoice;
	
	private String name;
	
	private String forgUnitId;
	private String fcreatorId;
	private String fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@Column(name = "FEnterPollID", length = 32)
	public String getFenterPollId() {
		return this.fenterPollId;
	}

	public void setFenterPollId(String fenterPollId) {
		this.fenterPollId = fenterPollId;
	}

	@Column(name = "FEnterID", length = 32)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
	}

	@Column(name = "FPollutantID", length = 32)
	public String getFpollutantId() {
		return this.fpollutantId;
	}

	public void setFpollutantId(String fpollutantId) {
		this.fpollutantId = fpollutantId;
	}

	@Column(name = "bIsChoice")
	public Boolean getBisChoice() {
		return this.bisChoice;
	}

	public void setBisChoice(Boolean bisChoice) {
		this.bisChoice = bisChoice;
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

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}