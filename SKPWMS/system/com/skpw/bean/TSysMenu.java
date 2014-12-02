package com.skpw.bean;

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
 * @author Administrator 菜单
 */

@Entity
@Table(name = "T_SYS_MenuItem")
public class TSysMenu {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FMenuID", unique = true, nullable = false)
	private String id;

	@Column(name = "FMenuCode")
	private String menuCode;

	@Column(name = "FMenuName")
	private String menuName;

	// @Column(name = "FParentID")
	// private String parentID;

	@ManyToOne
	@JoinColumn(name = "FParentID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TSysMenu parentMenu;

	@Column(name = "FUrl")
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public TSysMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(TSysMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
