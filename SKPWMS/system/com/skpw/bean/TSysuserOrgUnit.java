package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Administrator 用户组织表
 */
@Entity
@Table(name = "T_SYS_UserOrgUnit")
public class TSysuserOrgUnit implements Serializable {

	private static final long serialVersionUID = -9174787726094966464L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FUserOrgUnitID", unique = true, nullable = false)
	private String id;

	@ManyToOne
	@JoinColumn(name = "FOrgUnitID")
	private TSysorgUnit orgUnit;
	
	@OneToOne  
	@JoinColumn(name="FUserID")
	private TSysUserInfo userInfo; // 关联用户

	@Column(name = "FIsDefault")
	private Integer isDefault;// 是否是缺省组织

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TSysorgUnit getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(TSysorgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}

	public TSysUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(TSysUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

}
