package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_Bas_Zlkzq")
public class TBasZlkzq {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "wrybh")
	private String wrybh;//污染源编码

	@Column(name = "pfkbh")
	private String pfkbh;//排放口编号

	@Column(name = "pfkType")
	private String pfkType;//排口类型

	@Column(name = "zlkzqBd")
	private String zlkzqBd;//总量控制器绑定

	@Column(name = "zlkzqXh")
	private String zlkzqXh;//总量控制器序号

	@Column(name = "password")
	private String password;//访问密码

	@Column(name = "sjcsfs")
	private String sjcsfs;//数据传输方式

	@Column(name = "sccj")
	private String sccj;//生产厂家

	@Column(name = "status")
	private String status;//状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWrybh() {
		return wrybh;
	}

	public void setWrybh(String wrybh) {
		this.wrybh = wrybh;
	}

	public String getPfkbh() {
		return pfkbh;
	}

	public void setPfkbh(String pfkbh) {
		this.pfkbh = pfkbh;
	}

	public String getPfkType() {
		return pfkType;
	}

	public void setPfkType(String pfkType) {
		this.pfkType = pfkType;
	}

	public String getZlkzqBd() {
		return zlkzqBd;
	}

	public void setZlkzqBd(String zlkzqBd) {
		this.zlkzqBd = zlkzqBd;
	}

	public String getZlkzqXh() {
		return zlkzqXh;
	}

	public void setZlkzqXh(String zlkzqXh) {
		this.zlkzqXh = zlkzqXh;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSjcsfs() {
		return sjcsfs;
	}

	public void setSjcsfs(String sjcsfs) {
		this.sjcsfs = sjcsfs;
	}

	public String getSccj() {
		return sccj;
	}

	public void setSccj(String sccj) {
		this.sccj = sccj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
