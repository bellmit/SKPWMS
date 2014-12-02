package com.skpw.bean;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public class SuperEntity {
	private String forgUnitId;
	private String fcreatorId;
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp fcreatTime;
	private String flastEditId;
//	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp flastEditTime;
	private Boolean fisDisable;
	@Transient
	private String fversion;
	
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
	public String getFversion() {
		return fversion;
	}
	public void setFversion(String fversion) {
		this.fversion = fversion;
	}
	
	
}
