package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBasCreditGradeId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_CreditGrade", schema = "dbo", catalog = "SKPW_DEV")
public class TBasCreditGrade{


	private String fcreditGradeId;
	private String fcreditGradeCode;
	private String fcreditGradeName;
	private String forgUnitId;
	private String fcreatorId;
	private Timestamp fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;



	@Id
	@Column(name = "FCreditGradeID", length = 32)
	public String getFcreditGradeId() {
		return this.fcreditGradeId;
	}

	public void setFcreditGradeId(String fcreditGradeId) {
		this.fcreditGradeId = fcreditGradeId;
	}

	@Column(name = "FCreditGradeCode", length = 40)
	public String getFcreditGradeCode() {
		return this.fcreditGradeCode;
	}

	public void setFcreditGradeCode(String fcreditGradeCode) {
		this.fcreditGradeCode = fcreditGradeCode;
	}

	@Column(name = "FCreditGradeName", length = 100)
	public String getFcreditGradeName() {
		return this.fcreditGradeName;
	}

	public void setFcreditGradeName(String fcreditGradeName) {
		this.fcreditGradeName = fcreditGradeName;
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