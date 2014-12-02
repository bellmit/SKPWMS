package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Administrator 
 * 角色信息
 */

@Entity
@Table(name = "T_SYS_Role")
@JsonIgnoreProperties(value={"authorityRoles"})
public class TSysRole implements Serializable{

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FRoleID", unique = true, nullable = false)
	private String id;
	
	@Column(name="FRoleCode")
	private String rolecode;//角色编码
	
	@Column(name = "FRoleName")
	private String rolename;//角色名称	
	
	public TSysRole() {
		
	}

	public TSysRole(String id, String rolename) {
		
		this.id = id;
		this.rolename = rolename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRolecode() {
		return rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
