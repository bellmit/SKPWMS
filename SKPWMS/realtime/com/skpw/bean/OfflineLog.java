package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author hjy 脱机日志表
 */

@Entity
@Table(name="T_TC_OfflineLog")
public class OfflineLog {

	@Id
	@Column(name = "FOfflineLogID")
	private String id;
	@ManyToOne
	@JoinColumn(name = "FEnterID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TBasEnterprise enterprise;// 企业名称

	@ManyToOne
	@JoinColumn(name = "FControlerID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TTcControler ttcControler;// 控制器id
	
	

	@Column(name = "FStartDate")
	private String startdate;// 脱机开始时间

	@Column(name = "FEndDate")
	private String enddate;// 脱机结束时间

	@Column(name = "FOfflineMinute")
	private String offlineMinute;// 累计脱机时间

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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getOfflineMinute() {
		return offlineMinute;
	}

	public void setOfflineMinute(String offlineMinute) {
		this.offlineMinute = offlineMinute;
	}

	
	
	
}
