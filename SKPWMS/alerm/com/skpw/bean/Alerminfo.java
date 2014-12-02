package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_RT_ALERMINFO")
public class Alerminfo {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;

	@Column(name = "alermparam")
	private String alermparam;// 参数因子

	@Column(name = "alermtime")
	private String alermtime;// 报警时间

	@Column(name = "alermreason")
	private String alermreason;// 报警事由

	@Column(name = "alermvalue")
	private float alermvalue;// 报警值

	@Column(name = "alermstand")
	private String alermstand;// 报警标准

	@Column(name = "status")
	public String status;// 报警状态

	@ManyToOne
	@JoinColumn(name = "enterid")
	private TBasEnterprise enterprise; // 关联企业
	@ManyToOne
	@JoinColumn(name = "controlerid")
	private TTcControler ttcontroler; // 关联总量控制器
	@ManyToOne
	@JoinColumn(name = "outid")
	private TPsOutPermit outPermit; // 关联排污许可证
	@ManyToOne
	@JoinColumn(name = "pollutantid")
	private TBasPollutant pollutant; // 关联污染源因子

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlermparam() {
		return alermparam;
	}

	public void setAlermparam(String alermparam) {
		this.alermparam = alermparam;
	}

	public String getAlermtime() {
		return alermtime;
	}

	public void setAlermtime(String alermtime) {
		this.alermtime = alermtime;
	}

	public String getAlermreason() {
		return alermreason;
	}

	public void setAlermreason(String alermreason) {
		this.alermreason = alermreason;
	}

	public float getAlermvalue() {
		return alermvalue;
	}

	public void setAlermvalue(float alermvalue) {
		this.alermvalue = alermvalue;
	}

	public String getAlermstand() {
		return alermstand;
	}

	public void setAlermstand(String alermstand) {
		this.alermstand = alermstand;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
