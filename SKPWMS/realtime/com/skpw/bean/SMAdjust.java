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
 * @author hjy 月度数据调整表
 */

@Entity
@Table(name = "T_RT_SewageMonthAdjust")
public class SMAdjust {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FSMAdjustID", unique = true, nullable = false, length = 32)
	private String id;// 主键

	@ManyToOne
	@JoinColumn(name = "FEnterID", referencedColumnName = "FID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TBasEnterprise enterprise;// 企业名称

	@ManyToOne
	@JoinColumn(name = "FControlerID", referencedColumnName = "FID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TTcControler ttcControler;// 总量控制器

	@ManyToOne
	@JoinColumn(name = "FPollutantID", referencedColumnName = "FID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TBasPollutant pollutant; // 关联污染源因子

	@Column(name = "FMonthID")
	private String month;// 月度

	@Column(name = "FDischarge")
	private String discharge;// 月度排放量

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TBasEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TBasEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public TTcControler getTtcControler() {
		return ttcControler;
	}

	public void setTtcControler(TTcControler ttcControler) {
		this.ttcControler = ttcControler;
	}

	public TBasPollutant getPollutant() {
		return pollutant;
	}

	public void setPollutant(TBasPollutant pollutant) {
		this.pollutant = pollutant;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDischarge() {
		return discharge;
	}

	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}

}
