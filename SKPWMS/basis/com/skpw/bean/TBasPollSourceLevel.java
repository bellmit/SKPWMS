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
 * 污染源級別
 * 2014-10-23
 */
@Entity
@Table(name = "T_BAS_PollSourceLevel")
public class TBasPollSourceLevel implements Serializable{


	private static final long serialVersionUID = 1L;
	private String fpslevelId;
	private String fpslevelCode;
	private String fpslevelName;
	private String forgUnitId;
	private String fcreatorId;
	private String fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;


	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FPSLevelID", unique = true, nullable = false, length = 32)
	public String getFpslevelId() {
		return this.fpslevelId;
	}

	public void setFpslevelId(String fpslevelId) {
		this.fpslevelId = fpslevelId;
	}

	@Column(name = "FPSLevelCode", length = 60)
	public String getFpslevelCode() {
		return this.fpslevelCode;
	}

	public void setFpslevelCode(String fpslevelCode) {
		this.fpslevelCode = fpslevelCode;
	}

	@Column(name = "FPSLevelName", length = 100)
	public String getFpslevelName() {
		return this.fpslevelName;
	}

	public void setFpslevelName(String fpslevelName) {
		this.fpslevelName = fpslevelName;
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