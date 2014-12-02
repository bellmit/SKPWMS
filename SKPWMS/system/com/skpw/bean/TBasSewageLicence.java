package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Administrator 排污许可证信息  
 */
@Entity
@Table(name = "T_BAS_SewageLicence")
public class TBasSewageLicence {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	private String id;

	// @OneToOne
	// @JoinColumn(name = "wrybh", unique = true)
	// private TBasEnterprise tBasEnterprise;// 污染源编号

	@Column(name = "wrybh")
	private String wrybh;// 污染源编号

	@Column(name = "xkzbh")
	private String xkzbh;// 许可证编号
	@Column(name = "wrw")
	private String wrw;// 污染物
	@Column(name = "yxpfl")
	private float yxpfl;// 允许排放量
	@Column(name = "yxpfld")
	private float yxpfld;// 允许排放浓度
	@Column(name = "status")
	private Integer status;// 状态

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

	public String getWrw() {
		return wrw;
	}

	public void setWrw(String wrw) {
		this.wrw = wrw;
	}

	public String getXkzbh() {
		return xkzbh;
	}

	public void setXkzbh(String xkzbh) {
		this.xkzbh = xkzbh;
	}

	public float getYxpfl() {
		return yxpfl;
	}

	public void setYxpfl(float yxpfl) {
		this.yxpfl = yxpfl;
	}

	public float getYxpfld() {
		return yxpfld;
	}

	public void setYxpfld(float yxpfld) {
		this.yxpfld = yxpfld;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
