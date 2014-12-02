package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TPsOutSpoll entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PS_OutSPoll", schema = "dbo", catalog = "SKPW_DEV")
public class TPsOutSpollBak implements java.io.Serializable {

	// Fields

	private String foutSpollId;
	private String foutSewageId;
	private String foutPid;
	private String fpollutantId;
	private Double fmaxConcentration;
	private Double fdayMaxOut;
	private Double fmaxOut;
	private Double fquarterOut;
	private Double fmonthOut;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;
	private String fversion;

	// Constructors

	/** default constructor */
	public TPsOutSpollBak() {
	}

	/** minimal constructor */
	public TPsOutSpollBak(String foutSpollId, String fversion) {
		this.foutSpollId = foutSpollId;
		this.fversion = fversion;
	}

	/** full constructor */
	public TPsOutSpollBak(String foutSpollId, String foutSewageId, String foutPid,
			String fpollutantId, Double fmaxConcentration, Double fdayMaxOut,
			Double fmaxOut, Double fquarterOut, Double fmonthOut,
			String forgUnitId, String fcreatorId, Timestamp fcreatTime,
			String flastEditId, Timestamp flastEditTime, Boolean fisDisable,
			String fversion) {
		this.foutSpollId = foutSpollId;
		this.foutSewageId = foutSewageId;
		this.foutPid = foutPid;
		this.fpollutantId = fpollutantId;
		this.fmaxConcentration = fmaxConcentration;
		this.fdayMaxOut = fdayMaxOut;
		this.fmaxOut = fmaxOut;
		this.fquarterOut = fquarterOut;
		this.fmonthOut = fmonthOut;
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
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FOutSPollID", unique = true, nullable = false, length = 32)
	public String getFoutSpollId() {
		return this.foutSpollId;
	}

	public void setFoutSpollId(String foutSpollId) {
		this.foutSpollId = foutSpollId;
	}

	@Column(name = "FOutSewageID", length = 32)
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

	@Column(name = "FPollutantID", length = 32)
	public String getFpollutantId() {
		return this.fpollutantId;
	}

	public void setFpollutantId(String fpollutantId) {
		this.fpollutantId = fpollutantId;
	}

	@Column(name = "FMaxConcentration")
	public Double getFmaxConcentration() {
		return this.fmaxConcentration;
	}

	public void setFmaxConcentration(Double fmaxConcentration) {
		this.fmaxConcentration = fmaxConcentration;
	}

	@Column(name = "FDayMaxOut")
	public Double getFdayMaxOut() {
		return this.fdayMaxOut;
	}

	public void setFdayMaxOut(Double fdayMaxOut) {
		this.fdayMaxOut = fdayMaxOut;
	}

	@Column(name = "FMaxOut")
	public Double getFmaxOut() {
		return this.fmaxOut;
	}

	public void setFmaxOut(Double fmaxOut) {
		this.fmaxOut = fmaxOut;
	}

	@Column(name = "FQuarterOut")
	public Double getFquarterOut() {
		return this.fquarterOut;
	}

	public void setFquarterOut(Double fquarterOut) {
		this.fquarterOut = fquarterOut;
	}

	@Column(name = "FMonthOut")
	public Double getFmonthOut() {
		return this.fmonthOut;
	}

	public void setFmonthOut(Double fmonthOut) {
		this.fmonthOut = fmonthOut;
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

	@Column(name = "FVersion", nullable = false)
	public String getFversion() {
		return this.fversion;
	}

	public void setFversion(String fversion) {
		this.fversion = fversion;
	}

}