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
 * @author Administrator
 *	日志信息
 */
@Entity
@Table(name="T_SYS_LOG")
public class TSysLog {
	
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "userid")
	private TSysUserInfo tSysUserInfo;//关联用户
	
	@Column(name="loglevel")
	private Short loglevel;
	
	@Column(name = "operatetime", nullable = false, length = 35)
	private String operatetime;
	
	@Column(name = "operatetype")
	private Short operatetype;  //操作类型
	
	@Column(name = "logcontent", nullable = false, length = 2000)
	private String logcontent; //日志内容
	
	@Column(name = "broswer", length = 100)
	private String broswer;//用户浏览器类型
	
	@Column(name = "note", length = 300)
	private String note;//操作人ip
	
	
	
	public TSysLog() {
		super();
	}
	
	public TSysLog(Short loglevel, Short operatetype, String logcontent) {
		super();
		this.loglevel = loglevel;
		this.operatetype = operatetype;
		this.logcontent = logcontent;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TSysUserInfo gettSysUserInfo() {
		return tSysUserInfo;
	}
	public void settSysUserInfo(TSysUserInfo tSysUserInfo) {
		this.tSysUserInfo = tSysUserInfo;
	}
	public Short getLoglevel() {
		return loglevel;
	}
	public void setLoglevel(Short loglevel) {
		this.loglevel = loglevel;
	}
	
	public String getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
	public Short getOperatetype() {
		return operatetype;
	}
	public void setOperatetype(Short operatetype) {
		this.operatetype = operatetype;
	}
	public String getLogcontent() {
		return logcontent;
	}
	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}
	public String getBroswer() {
		return broswer;
	}
	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
