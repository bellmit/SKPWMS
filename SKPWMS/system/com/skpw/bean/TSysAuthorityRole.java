package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 角色权限表
 */
@Entity
@Table(name="T_SYS_AuthorityRole")
public class TSysAuthorityRole implements Serializable {

	
	private static final long serialVersionUID = 1352575290140797485L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "FAuthRoleID")
	private String id;

	@ManyToOne
	@JoinColumn(name = "FRoleID")
	private TSysRole role;
	@ManyToOne
	@JoinColumn(name = "FAuthorityID")
	private TSysAuthority authority;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TSysRole getRole() {
		return role;
	}

	public void setRole(TSysRole role) {
		this.role = role;
	}

	public TSysAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(TSysAuthority authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
