package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SMS_Templete")
public class TSmsTemplete extends SuperEntity{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fTempleteID;	//模板ID	char

	private String fContent;	//短信内容	varchar

	public String getfTempleteID() {
		return fTempleteID;
	}

	public void setfTempleteID(String fTempleteID) {
		this.fTempleteID = fTempleteID;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}


}
