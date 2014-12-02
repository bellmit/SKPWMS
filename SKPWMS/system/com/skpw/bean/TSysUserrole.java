package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Administrator
 *用户角色信息
 */
@Entity
@Table(name = "T_SYS_UserRole")
@JsonIgnoreProperties(value={"userInfo","role"})
public class TSysUserrole implements Serializable {

	private static final long serialVersionUID = 2762164057702598379L;

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "FUserRoleID")
	private String id;

	@ManyToOne
	@JoinColumn(name = "FUserID")
	@NotFound(action=NotFoundAction.IGNORE) 
	private TSysUserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "FRoleID")
	@NotFound(action=NotFoundAction.IGNORE) 
	private TSysRole role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TSysUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(TSysUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public TSysRole getRole() {
		return role;
	}

	public void setRole(TSysRole role) {
		this.role = role;
	}

}
