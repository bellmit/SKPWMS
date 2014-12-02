package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_SMS_ChoiceItem")
public class TSmsChoiceItem extends SuperEntity{
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fItemID;	//短信项目ID	char

	private String fCNField;	//中文项目名称	varchar

	private String fENField;	//英文项目名称	varchar

	private String fDesc;	//说明	varchar

	public String getfItemID() {
		return fItemID;
	}

	public void setfItemID(String fItemID) {
		this.fItemID = fItemID;
	}

	public String getfCNField() {
		return fCNField;
	}

	public void setfCNField(String fCNField) {
		this.fCNField = fCNField;
	}

	public String getfENField() {
		return fENField;
	}

	public void setfENField(String fENField) {
		this.fENField = fENField;
	}

	public String getfDesc() {
		return fDesc;
	}

	public void setfDesc(String fDesc) {
		this.fDesc = fDesc;
	}


}
