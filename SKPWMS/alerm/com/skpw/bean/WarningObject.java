package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy 报警对象
 */
@Entity
@Table(name = "T_BAS_WarningObject")
public class WarningObject {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWarningObjID", unique = true, nullable = false)
	private String id;

	@Column(name = "FWarningObjName")
	private String warningObjName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarningObjName() {
		return warningObjName;
	}

	public void setWarningObjName(String warningObjName) {
		this.warningObjName = warningObjName;
	}

}
