package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy 充值信息明细
 */
@Entity
@Table(name = "T_TEST_TICRechargeDetail")
public class TICRechargeDetailsBak implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;

	@Column(name = "icnum")
	private String icnum;// ic卡号

	@Column(name = "pfxkzl")
	private float pfxkzl;// 年度排放许可总量
//	@Column(name = "zxbz")
//	private float zxbz; // 执行标准

	@Column(name = "czrq")
	private String czrq;// 充值日期

	@Column(name = "wryyz")
	private String wryyz;// 污染源因子
	@Column(name = "czfs")
	private String czfs;// 充值方式

	@Column(name = "cznd")
	private String cznd;// 充值年度

	@Column(name = "czpfl")
	private float czpfl;// 充值排放量

	@Column(name = "xkpfl")
	private float xkpfl;// 许可排放量

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIcnum() {
		return icnum;
	}

	public void setIcnum(String icnum) {
		this.icnum = icnum;
	}

	public String getCzrq() {
		return czrq;
	}

	public void setCzrq(String czrq) {
		this.czrq = czrq;
	}

	public String getCznd() {
		return cznd;
	}

	public void setCznd(String cznd) {
		this.cznd = cznd;
	}

	public float getCzpfl() {
		return czpfl;
	}

	public void setCzpfl(float czpfl) {
		this.czpfl = czpfl;
	}

	public float getXkpfl() {
		return xkpfl;
	}

	public void setXkpfl(float xkpfl) {
		this.xkpfl = xkpfl;
	}

	public float getPfxkzl() {
		return pfxkzl;
	}

	public void setPfxkzl(float pfxkzl) {
		this.pfxkzl = pfxkzl;
	}

//	public float getZxbz() {
//		return zxbz;
//	}
//
//	public void setZxbz(float zxbz) {
//		this.zxbz = zxbz;
//	}

	public String getWryyz() {
		return wryyz;
	}

	public void setWryyz(String wryyz) {
		this.wryyz = wryyz;
	}

	public String getCzfs() {
		return czfs;
	}

	public void setCzfs(String czfs) {
		this.czfs = czfs;
	}
	
	
}
