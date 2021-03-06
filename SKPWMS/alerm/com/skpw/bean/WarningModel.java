package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy 预警方式
 */

@Entity
@Table(name="T_BAS_WarningModel")
public class WarningModel {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWarningModelID", unique = true, nullable = false)
	private String id;

	@Column(name = "FWarningModelName")
	private String warningModelName;// 方式名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarningModelName() {
		return warningModelName;
	}

	public void setWarningModelName(String warningModelName) {
		this.warningModelName = warningModelName;
	}

}
