package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_BAS_PollutantType")
public class TBasPollutantType {
	@Id
	private String fPollTypeID;	//污染物类别ID	char

	private String fPollTypeCode;	//污染物类别编号	varchar

	private String fPollTypeName;	//污染物类别名称	varchar

	public String getfPollTypeID() {
		return fPollTypeID;
	}

	public void setfPollTypeID(String fPollTypeID) {
		this.fPollTypeID = fPollTypeID;
	}

	public String getfPollTypeCode() {
		return fPollTypeCode;
	}

	public void setfPollTypeCode(String fPollTypeCode) {
		this.fPollTypeCode = fPollTypeCode;
	}

	public String getfPollTypeName() {
		return fPollTypeName;
	}

	public void setfPollTypeName(String fPollTypeName) {
		this.fPollTypeName = fPollTypeName;
	}

	
}
