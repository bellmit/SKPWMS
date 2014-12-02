package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Administrator
 * 
 *         职位信息表
 */
@Entity
@Table(name = "T_SYS_Job")
//@JsonIgnoreProperties(value={"orgUnit"})
public class TSysjob implements Serializable {

	private static final long serialVersionUID = 2717167052917459986L;
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "FJobID")
	private String id;// 职位id

//	@Column(name = "FDepID")
//	private String depid;// 部门id

	@Column(name = "FJobCode")
	private String jobCode;// 职位编号

	@Column(name = "FJobName")
	private String jobname;// 职位名称

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FParentID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TSysjob parentJob;// 父职位

	@Column(name = "FLongCode")
	private String longCode; // 职务长编码

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FOrgUnitID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TSysorgUnit orgUnit;// 所属组织

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getDepid() {
//		return depid;
//	}
//
//	public void setDepid(String depid) {
//		this.depid = depid;
//	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}
	
	public TSysjob getParentJob() {
		return parentJob;
	}

	public void setParentJob(TSysjob parentJob) {
		this.parentJob = parentJob;
	}

	public String getLongCode() {
		return longCode;
	}

	public void setLongCode(String longCode) {
		this.longCode = longCode;
	}
	
	public TSysorgUnit getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(TSysorgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}
	
}
