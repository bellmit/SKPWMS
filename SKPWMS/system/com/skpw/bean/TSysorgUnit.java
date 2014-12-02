package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 组织信息表
 */

@Entity
@Table(name = "T_SYS_OrgUnit")
public class TSysorgUnit implements Serializable {

	private static final long serialVersionUID = 7413360642594148285L;
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FOrgUnitID", unique = true, nullable = false)
	private String id;// 组织id

	@Column(name = "FOrgUnitCode")
	private String orgUnitCode;// 组织编码

	@Column(name = "FOrgUnitName")
	private String orgUnitName;// 组织名称

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FParentID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TSysorgUnit parentOrgUnit;// 父级组织id

	@Column(name = "FLongCode")
	private String longCode;// 组织长编码

	@Column(name = "Fproperty")
	private String property;// 组织属性

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	public TSysorgUnit getParentOrgUnit() {
		return parentOrgUnit;
	}

	public void setParentOrgUnit(TSysorgUnit parentOrgUnit) {
		this.parentOrgUnit = parentOrgUnit;
	}

	public String getLongCode() {
		return longCode;
	}

	public void setLongCode(String longCode) {
		this.longCode = longCode;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
