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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Administrator 权限信息
 */
@Entity
@Table(name = "T_SYS_MenuAuthority")
public class TSysAuthority implements Serializable {

	private static final long serialVersionUID = -6233024907268437667L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FAuthorityID", unique = true, nullable = false)
	private String id;
	@ManyToOne
	@JoinColumn(name = "FMenuID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TSysMenu sysMenu;// 关联菜单

	@Column(name = "FAuthorityName")
	private String authorityName;// 权限名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TSysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(TSysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
