package com.skpw.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TBasEnterprise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BAS_Enterprise", schema = "dbo", catalog = "SKPW_DEV")
public class TBasEnterprise implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fenterId;	
	private Integer fid;
	private String fenterCode;
	private String fenterName;
	private String fsimName;
	private Date fstartUpDate;  //投产日期
	private String fprovinceId;
	private String fcityId;
	private String fcountyId;
	private TBasCounty tBasCounty;
	private String ftownId;
	private String fvillageId;
	private String funitClassId;
	private String findustryTypeId;
	private TBasIndustryType tBasIndustryType;
	private String fbasinId;
	private String fpstypeId;
	private String fpslevelId;
	private String fregisterTypeId;
	private String fcompanyScaleId;
	private String frshipId;
	private String fregAddress;
	private String fprodAddress;
	private String fcorpCode;
	private String fcorpName;
	private String fphone;
	private String ffax;
	private String fpostCode;
	private String femail;
	private String fmobile;
	private Double flongitude;
	private Integer flongDegree;
	private Integer flongMinute;
	private Double flongSecond;
	private Double flatitude;
	private Integer flatDegree;
	private Integer flatMinute;
	private Double flatSecond;
	private String fempId;
	private String fenvironMan;
	private String fenvironMobile;
	private String fenvironTel;
	private Boolean fisSewage;
	private Boolean fisWasteGas;
	private Boolean fisSolidWaste;
	private Boolean fisNoise;
	private TSysorgUnit tSysorgUnit;
	private String forgUnitId;
	private String fcreatorId;
	private String fcreatTime;
	private String flastEditId;
	private Timestamp flastEditTime;
	private Boolean fisDisable;
	private String forgCode;
	private String fenvironLeader;
	private String fenvironLeaderMobile;
	private String fempMobile;
	private String fcorpMobile;
	private List<String> flcList;
	

	@Id
	@Column(name = "FEnterID", unique = true, nullable = false, length = 32)
	public String getFenterId() {
		return this.fenterId;
	}

	public void setFenterId(String fenterId) {
		this.fenterId = fenterId;
	}

	@Column(name = "FEnterCode", length = 60)
	public String getFenterCode() {
		return this.fenterCode;
	}

	public void setFenterCode(String fenterCode) {
		this.fenterCode = fenterCode;
	}

	@Column(name = "FEnterName", length = 100)
	public String getFenterName() {
		return this.fenterName;
	}

	public void setFenterName(String fenterName) {
		this.fenterName = fenterName;
	}

	@Column(name = "FSimName", length = 100)
	public String getFsimName() {
		return this.fsimName;
	}

	public void setFsimName(String fsimName) {
		this.fsimName = fsimName;
	}

	@Column(name = "FStartUpDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getFstartUpDate() {
		return this.fstartUpDate;
	}

	public void setFstartUpDate(Date fstartUpDate) {
		this.fstartUpDate = fstartUpDate;
	}

	@Column(name = "FProvinceID", length = 32)
	public String getFprovinceId() {
		return this.fprovinceId;
	}

	public void setFprovinceId(String fprovinceId) {
		this.fprovinceId = fprovinceId;
	}

	@Column(name = "FCityID", length = 32)
	public String getFcityId() {
		return this.fcityId;
	}

	public void setFcityId(String fcityId) {
		this.fcityId = fcityId;
	}


	@Column(name = "FTownID", length = 32)
	public String getFtownId() {
		return this.ftownId;
	}

	public void setFtownId(String ftownId) {
		this.ftownId = ftownId;
	}

	@Column(name = "FVillageID", length = 32)
	public String getFvillageId() {
		return this.fvillageId;
	}

	public void setFvillageId(String fvillageId) {
		this.fvillageId = fvillageId;
	}

	@Column(name = "FUnitClassID", length = 32)
	public String getFunitClassId() {
		return this.funitClassId;
	}

	public void setFunitClassId(String funitClassId) {
		this.funitClassId = funitClassId;
	}

	@Column(name = "FBasinID", length = 32)
	public String getFbasinId() {
		return this.fbasinId;
	}

	public void setFbasinId(String fbasinId) {
		this.fbasinId = fbasinId;
	}

	@Column(name = "FPSTypeID", length = 32)
	public String getFpstypeId() {
		return this.fpstypeId;
	}

	public void setFpstypeId(String fpstypeId) {
		this.fpstypeId = fpstypeId;
	}

	@Column(name = "FPSLevelID", length = 32)
	public String getFpslevelId() {
		return this.fpslevelId;
	}

	public void setFpslevelId(String fpslevelId) {
		this.fpslevelId = fpslevelId;
	}

	@Column(name = "FRegisterTypeID", length = 32)
	public String getFregisterTypeId() {
		return this.fregisterTypeId;
	}

	public void setFregisterTypeId(String fregisterTypeId) {
		this.fregisterTypeId = fregisterTypeId;
	}

	@Column(name = "FCompanyScaleID", length = 32)
	public String getFcompanyScaleId() {
		return this.fcompanyScaleId;
	}

	public void setFcompanyScaleId(String fcompanyScaleId) {
		this.fcompanyScaleId = fcompanyScaleId;
	}

	@Column(name = "FRShipID", length = 32)
	public String getFrshipId() {
		return this.frshipId;
	}

	public void setFrshipId(String frshipId) {
		this.frshipId = frshipId;
	}

	@Column(name = "FRegAddress", length = 1000)
	public String getFregAddress() {
		return this.fregAddress;
	}

	public void setFregAddress(String fregAddress) {
		this.fregAddress = fregAddress;
	}

	@Column(name = "FProdAddress", length = 1000)
	public String getFprodAddress() {
		return this.fprodAddress;
	}

	public void setFprodAddress(String fprodAddress) {
		this.fprodAddress = fprodAddress;
	}

	@Column(name = "FCorpCode", length = 100)
	public String getFcorpCode() {
		return this.fcorpCode;
	}

	public void setFcorpCode(String fcorpCode) {
		this.fcorpCode = fcorpCode;
	}

	@Column(name = "FCorpName", length = 100)
	public String getFcorpName() {
		return this.fcorpName;
	}

	public void setFcorpName(String fcorpName) {
		this.fcorpName = fcorpName;
	}

	@Column(name = "FPhone", length = 100)
	public String getFphone() {
		return this.fphone;
	}

	public void setFphone(String fphone) {
		this.fphone = fphone;
	}

	@Column(name = "FFax", length = 100)
	public String getFfax() {
		return this.ffax;
	}

	public void setFfax(String ffax) {
		this.ffax = ffax;
	}

	@Column(name = "FPostCode", length = 100)
	public String getFpostCode() {
		return this.fpostCode;
	}

	public void setFpostCode(String fpostCode) {
		this.fpostCode = fpostCode;
	}

	@Column(name = "FEmail", length = 100)
	public String getFemail() {
		return this.femail;
	}

	public void setFemail(String femail) {
		this.femail = femail;
	}

	@Column(name = "FMobile", length = 100)
	public String getFmobile() {
		return this.fmobile;
	}

	public void setFmobile(String fmobile) {
		this.fmobile = fmobile;
	}

	@Column(name = "FLongitude", precision = 12, scale = 6)
	public Double getFlongitude() {
		return this.flongitude;
	}

	public void setFlongitude(Double flongitude) {
		this.flongitude = flongitude;
	}

	@Column(name = "FLongDegree")
	public Integer getFlongDegree() {
		return this.flongDegree;
	}

	public void setFlongDegree(Integer flongDegree) {
		this.flongDegree = flongDegree;
	}

	@Column(name = "FLongMinute")
	public Integer getFlongMinute() {
		return this.flongMinute;
	}

	public void setFlongMinute(Integer flongMinute) {
		this.flongMinute = flongMinute;
	}

	@Column(name = "FLongSecond")
	public Double getFlongSecond() {
		return this.flongSecond;
	}

	public void setFlongSecond(Double flongSecond) {
		this.flongSecond = flongSecond;
	}

	@Column(name = "FLatitude", precision = 12, scale = 6)
	public Double getFlatitude() {
		return this.flatitude;
	}

	public void setFlatitude(Double flatitude) {
		this.flatitude = flatitude;
	}

	@Column(name = "FLatDegree")
	public Integer getFlatDegree() {
		return this.flatDegree;
	}

	public void setFlatDegree(Integer flatDegree) {
		this.flatDegree = flatDegree;
	}

	@Column(name = "FLatMinute")
	public Integer getFlatMinute() {
		return this.flatMinute;
	}

	public void setFlatMinute(Integer flatMinute) {
		this.flatMinute = flatMinute;
	}

	@Column(name = "FLatSecond")
	public Double getFlatSecond() {
		return this.flatSecond;
	}

	public void setFlatSecond(Double flatSecond) {
		this.flatSecond = flatSecond;
	}

	@Column(name = "FEmpID", length = 32)
	public String getFempId() {
		return this.fempId;
	}

	public void setFempId(String fempId) {
		this.fempId = fempId;
	}

	@Column(name = "FEnvironMan", length = 100)
	public String getFenvironMan() {
		return this.fenvironMan;
	}

	public void setFenvironMan(String fenvironMan) {
		this.fenvironMan = fenvironMan;
	}

	@Column(name = "FEnvironMobile", length = 100)
	public String getFenvironMobile() {
		return this.fenvironMobile;
	}

	public void setFenvironMobile(String fenvironMobile) {
		this.fenvironMobile = fenvironMobile;
	}

	@Column(name = "FEnvironTel", length = 100)
	public String getFenvironTel() {
		return this.fenvironTel;
	}

	public void setFenvironTel(String fenvironTel) {
		this.fenvironTel = fenvironTel;
	}

	@Column(name = "FIsSewage")
	public Boolean getFisSewage() {
		return this.fisSewage;
	}

	public void setFisSewage(Boolean fisSewage) {
		this.fisSewage = fisSewage;
	}

	@Column(name = "FIsWasteGas")
	public Boolean getFisWasteGas() {
		return this.fisWasteGas;
	}

	public void setFisWasteGas(Boolean fisWasteGas) {
		this.fisWasteGas = fisWasteGas;
	}

	@Column(name = "FIsSolidWaste")
	public Boolean getFisSolidWaste() {
		return this.fisSolidWaste;
	}

	public void setFisSolidWaste(Boolean fisSolidWaste) {
		this.fisSolidWaste = fisSolidWaste;
	}

	@Column(name = "FIsNoise")
	public Boolean getFisNoise() {
		return this.fisNoise;
	}

	public void setFisNoise(Boolean fisNoise) {
		this.fisNoise = fisNoise;
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

	@Column(name = "FOrgCode", length = 200)
	public String getForgCode() {
		return this.forgCode;
	}

	public void setForgCode(String forgCode) {
		this.forgCode = forgCode;
	}

	@Column(name = "FEnvironLeader", length = 100)
	public String getFenvironLeader() {
		return this.fenvironLeader;
	}

	public void setFenvironLeader(String fenvironLeader) {
		this.fenvironLeader = fenvironLeader;
	}

	@Column(name = "FEnvironLeaderMobile", length = 100)
	public String getFenvironLeaderMobile() {
		return this.fenvironLeaderMobile;
	}

	public void setFenvironLeaderMobile(String fenvironLeaderMobile) {
		this.fenvironLeaderMobile = fenvironLeaderMobile;
	}

	@Column(name = "FIndustryTypeID")
	public String getFindustryTypeId() {
		return findustryTypeId;
	}

	public void setFindustryTypeId(String findustryTypeId) {
		this.findustryTypeId = findustryTypeId;
	}

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FIndustryTypeID", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	public TBasIndustryType gettBasIndustryType() {
		return tBasIndustryType;
	}

	public void settBasIndustryType(TBasIndustryType tBasIndustryType) {
		this.tBasIndustryType = tBasIndustryType;
	}

	
	@Column(name = "FCountyID", length = 32)
	public String getFcountyId() {
		return this.fcountyId;
	}

	public void setFcountyId(String fcountyId) {
		this.fcountyId = fcountyId;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FCountyID", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	public TBasCounty gettBasCounty() {
		return tBasCounty;
	}

	public void settBasCounty(TBasCounty tBasCounty) {
		this.tBasCounty = tBasCounty;
	}

	@Column(name = "FEmpMobile", length = 32)
	public String getFempMobile() {
		return fempMobile;
	}

	public void setFempMobile(String fempMobile) {
		this.fempMobile = fempMobile;
	}

	@Column(name = "FCorpMobile", length = 32)
	public String getFcorpMobile() {
		return fcorpMobile;
	}

	public void setFcorpMobile(String fcorpMobile) {
		this.fcorpMobile = fcorpMobile;
	}


	@Column(name = "FOrgUnitID", length = 32)
	public String getForgUnitId() {
		return this.forgUnitId;
	}

	public void setForgUnitId(String forgUnitId) {
		this.forgUnitId = forgUnitId;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "FOrgUnitID", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	public TSysorgUnit gettSysorgUnit() {
		return tSysorgUnit;
	}

	public void settSysorgUnit(TSysorgUnit tSysorgUnit) {
		this.tSysorgUnit = tSysorgUnit;
	}
	@GeneratedValue(strategy = GenerationType.AUTO) 
    @Column(name = "FID",nullable=false,insertable=false,updatable=false,columnDefinition="numeric(19,0) IDENTITY")
	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Transient
	public List<String> getFlcList() {
		return flcList;
	}

	public void setFlcList(List<String> flcList) {
		this.flcList = flcList;
	}
	
	
}