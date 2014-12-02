package com.skpw.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Administrator 用户信息
 */
/**
 * @author hjy
 * 
 *         2014-9-19
 */
@Entity
@Table(name = "T_SYS_USERINFO")
@JsonIgnoreProperties(value = { "userOrgUnit" })
public class TSysUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String username; // 用户名
	private String usercount;// 用户别名
	private String password; // 密码
	private String workUnit; // 工作单位
	private String phone; // 联系方式
	private String email;// 邮箱
	private String roleStr;
	private TSysuserOrgUnit userOrgUnit;// 关联用户组织表	
	private TBasEnterprise enterprise;// 关联企业

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "workUnit")
	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public TSysuserOrgUnit getUserOrgUnit() {
		return userOrgUnit;
	}

	public void setUserOrgUnit(TSysuserOrgUnit userOrgUnit) {
		this.userOrgUnit = userOrgUnit;
	}

	@Transient
	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	@Column(name = "usercount")
	public String getUsercount() {
		return usercount;
	}

	public void setUsercount(String usercount) {
		this.usercount = usercount;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fenterid")
	public TBasEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TBasEnterprise enterprise) {
		this.enterprise = enterprise;
	}

}
