package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy 预警日志表
 */
@Entity
@Table(name = "T_EWS_WarningLog")
public class WarningLog {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWarningLogID", unique = true, nullable = false)
	private String id;

	@ManyToOne
	@JoinColumn(name = "FEnterID",referencedColumnName="FID")
	private TBasEnterprise enterprise; // 关联企业
	@ManyToOne
	@JoinColumn(name = "FControlerID")
	private TTcControler ttcontroler; // 关联总量控制器
	@ManyToOne
	@JoinColumn(name = "FOutPID")
	private TPsOutPermit outPermit; // 关联排污许可证
	@ManyToOne
	@JoinColumn(name = "FPollutantID")
	private TBasPollutant pollutant; // 关联污染源因子

	@ManyToOne
	@JoinColumn(name = "FWarningItemID")
	private WarningItem warningItem;// 关联预警项目

	@Column(name = "FThreshold")
	private String threshold;// 预警阀值

	@Column(name = "FRealValue")
	private String realValue;// 真实值

	@Column(name = "FStdValue")
	private String stdValue;// 标准值

	@Column(name = "FContent")
	private String content;// 报警内容

	@Column(name = "FWarningTime")
	private String warningTime;// 报警时间

	@Column(name = "FProcDateTime")
	private String procDateTime;// 处理时间

	@Column(name = "FStatus")
	private String status;// 状态

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

	public TTcControler getTtcontroler() {
		return ttcontroler;
	}

	public void setTtcontroler(TTcControler ttcontroler) {
		this.ttcontroler = ttcontroler;
	}

	public TPsOutPermit getOutPermit() {
		return outPermit;
	}

	public void setOutPermit(TPsOutPermit outPermit) {
		this.outPermit = outPermit;
	}

	public TBasPollutant getPollutant() {
		return pollutant;
	}

	public void setPollutant(TBasPollutant pollutant) {
		this.pollutant = pollutant;
	}

	public WarningItem getWarningItem() {
		return warningItem;
	}

	public void setWarningItem(WarningItem warningItem) {
		this.warningItem = warningItem;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getRealValue() {
		return realValue;
	}

	public void setRealValue(String realValue) {
		this.realValue = realValue;
	}

	public String getStdValue() {
		return stdValue;
	}

	public void setStdValue(String stdValue) {
		this.stdValue = stdValue;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWarningTime() {
		return warningTime;
	}

	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}

	public String getProcDateTime() {
		return procDateTime;
	}

	public void setProcDateTime(String procDateTime) {
		this.procDateTime = procDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
