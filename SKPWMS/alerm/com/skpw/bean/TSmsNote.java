package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SMS_Note")
public class TSmsNote {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fNoteID;	//短信内容ID	char

	private String fEnterID;	//企业ID	char

	private String fContent;	//短信内容	varchar

	public String getfNoteID() {
		return fNoteID;
	}

	public void setfNoteID(String fNoteID) {
		this.fNoteID = fNoteID;
	}

	public String getfEnterID() {
		return fEnterID;
	}

	public void setfEnterID(String fEnterID) {
		this.fEnterID = fEnterID;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}


}
