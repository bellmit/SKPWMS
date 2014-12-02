package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 总量控制器实体
 */
@Entity
@Table(name = "T_TC_Controler", schema = "dbo", catalog = "SKPW_DEV")
public class TTcControler implements Serializable{

	// Fields

	private String fcontrolerId;
	private Integer fid;
	private String fenterId;
	private String fctrlerCode;
	private String fctrlerName;
	private String fctrlerSn;
	private String faddress;
	private String fpost;
	private String faddress2;
	private String fpost2;
	private String faddress3;
	private String fpost3;
	private Short fcommunicationType;
	private String fuserName;
	private String fuserUnit;
	private String fuserDate;
	private String fstartupDate;
	private String fmanuFactory;
	private String flinkMan;
	private String fphone;
	private String foperateUnit;
	private String foulinkman;
	private String fpassword;
	private Integer finterval;
	private Boolean fisReportDay;
	private Boolean fisReportHour;
	private String fupdateDate;
	private Integer fstatus;
	private String forgUnitId;
	private String fcreatorId;
	private String fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;
	private String fpollTypeCode;

	// Property accessors
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FControlerID", unique = true, nullable = false, length = 32)
	public String getFcontrolerId() {
		return this.fcontrolerId;
	}

	public void setFcontrolerId(String fcontrolerId) {
		this.fcontrolerId = fcontrolerId;
	}

	@Column(name = "FEnterID", length = 32)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
	}

	@Column(name = "FCtrlerCode", length = 60)
	public String getFctrlerCode() {
		return this.fctrlerCode;
	}

	public void setFctrlerCode(String fctrlerCode) {
		this.fctrlerCode = fctrlerCode;
	}

	@Column(name = "FctrlerName", length = 100)
	public String getFctrlerName() {
		return this.fctrlerName;
	}

	public void setFctrlerName(String fctrlerName) {
		this.fctrlerName = fctrlerName;
	}

	@Column(name = "FCtrlerSN", length = 100)
	public String getFctrlerSn() {
		return this.fctrlerSn;
	}

	public void setFctrlerSn(String fctrlerSn) {
		this.fctrlerSn = fctrlerSn;
	}

	@Column(name = "FAddress", length = 40)
	public String getFaddress() {
		return this.faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}

	@Column(name = "FPost", length = 40)
	public String getFpost() {
		return this.fpost;
	}

	public void setFpost(String fpost) {
		this.fpost = fpost;
	}

	@Column(name = "FAddress2", length = 40)
	public String getFaddress2() {
		return this.faddress2;
	}

	public void setFaddress2(String faddress2) {
		this.faddress2 = faddress2;
	}

	@Column(name = "FPost2", length = 40)
	public String getFpost2() {
		return this.fpost2;
	}

	public void setFpost2(String fpost2) {
		this.fpost2 = fpost2;
	}

	@Column(name = "FAddress3", length = 40)
	public String getFaddress3() {
		return this.faddress3;
	}

	public void setFaddress3(String faddress3) {
		this.faddress3 = faddress3;
	}

	@Column(name = "FPost3", length = 40)
	public String getFpost3() {
		return this.fpost3;
	}

	public void setFpost3(String fpost3) {
		this.fpost3 = fpost3;
	}

	@Column(name = "FCommunicationType")
	public Short getFcommunicationType() {
		return this.fcommunicationType;
	}

	public void setFcommunicationType(Short fcommunicationType) {
		this.fcommunicationType = fcommunicationType;
	}

	@Column(name = "FUserName", length = 60)
	public String getFuserName() {
		return this.fuserName;
	}

	public void setFuserName(String fuserName) {
		this.fuserName = fuserName;
	}

	@Column(name = "FUserUnit", length = 100)
	public String getFuserUnit() {
		return this.fuserUnit;
	}

	public void setFuserUnit(String fuserUnit) {
		this.fuserUnit = fuserUnit;
	}

	@Column(name = "FUserDate")
	public String getFuserDate() {
		return this.fuserDate;
	}

	public void setFuserDate(String fuserDate) {
		this.fuserDate = fuserDate;
	}

	@Column(name = "FStartupDate")
	public String getFstartupDate() {
		return this.fstartupDate;
	}

	public void setFstartupDate(String fstartupDate) {
		this.fstartupDate = fstartupDate;
	}

	@Column(name = "FManuFactory", length = 100)
	public String getFmanuFactory() {
		return this.fmanuFactory;
	}

	public void setFmanuFactory(String fmanuFactory) {
		this.fmanuFactory = fmanuFactory;
	}

	@Column(name = "FLinkMan", length = 100)
	public String getFlinkMan() {
		return this.flinkMan;
	}

	public void setFlinkMan(String flinkMan) {
		this.flinkMan = flinkMan;
	}

	@Column(name = "FPhone", length = 100)
	public String getFphone() {
		return this.fphone;
	}

	public void setFphone(String fphone) {
		this.fphone = fphone;
	}

	@Column(name = "FOperateUnit", length = 100)
	public String getFoperateUnit() {
		return this.foperateUnit;
	}

	public void setFoperateUnit(String foperateUnit) {
		this.foperateUnit = foperateUnit;
	}

	@Column(name = "FOULinkman", length = 100)
	public String getFoulinkman() {
		return this.foulinkman;
	}

	public void setFoulinkman(String foulinkman) {
		this.foulinkman = foulinkman;
	}

	@Column(name = "FPassword", length = 100)
	public String getFpassword() {
		return this.fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}

	@Column(name = "FInterval")
	public Integer getFinterval() {
		return this.finterval;
	}

	public void setFinterval(Integer finterval) {
		this.finterval = finterval;
	}

	@Column(name = "FIsReportDay")
	public Boolean getFisReportDay() {
		return this.fisReportDay;
	}

	public void setFisReportDay(Boolean fisReportDay) {
		this.fisReportDay = fisReportDay;
	}

	@Column(name = "FIsReportHour")
	public Boolean getFisReportHour() {
		return this.fisReportHour;
	}

	public void setFisReportHour(Boolean fisReportHour) {
		this.fisReportHour = fisReportHour;
	}

	@Column(name = "FUpdateDate", length = 23)
	public String getFupdateDate() {
		return this.fupdateDate;
	}

	public void setFupdateDate(String fupdateDate) {
		this.fupdateDate = fupdateDate;
	}

	@Column(name = "FStatus")
	public Integer getFstatus() {
		return this.fstatus;
	}

	public void setFstatus(Integer fstatus) {
		this.fstatus = fstatus;
	}

	@Column(name = "FOrgUnitID", length = 32)
	public String getForgUnitId() {
		return this.forgUnitId;
	}

	public void setForgUnitId(String forgUnitId) {
		this.forgUnitId = forgUnitId;
	}

	@Column(name = "FCreatorID", length = 32)
	public String getFcreatorId() {
		return this.fcreatorId;
	}

	public void setFcreatorId(String fcreatorId) {
		this.fcreatorId = fcreatorId;
	}

	@Column(name = "FCreatTime", length = 23)
	public String getFcreatTime() {
		return this.fcreatTime;
	}

	public void setFcreatTime(String fcreatTime) {
		this.fcreatTime = fcreatTime;
	}

	@Column(name = "FLastEditID", length = 32)
	public String getFlastEditId() {
		return this.flastEditId;
	}

	public void setFlastEditId(String flastEditId) {
		this.flastEditId = flastEditId;
	}

	@Column(name = "FLastEditTime", length = 23)
	public Timestamp getFlastEditTime() {
		return this.flastEditTime;
	}

	public void setFlastEditTime(Timestamp flastEditTime) {
		this.flastEditTime = flastEditTime;
	}

	@Column(name = "FIsDisable")
	public Boolean getFisDisable() {
		return this.fisDisable;
	}

	public void setFisDisable(Boolean fisDisable) {
		this.fisDisable = fisDisable;
	}

	@GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "FID",nullable=false,insertable=false,updatable=false,columnDefinition="numeric(19,0) IDENTITY")
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Column(name = "FPollTypeCode", length = 100)
	public String getFpollTypeCode() {
		return fpollTypeCode;
	}

	public void setFpollTypeCode(String fpollTypeCode) {
		this.fpollTypeCode = fpollTypeCode;
	}

}