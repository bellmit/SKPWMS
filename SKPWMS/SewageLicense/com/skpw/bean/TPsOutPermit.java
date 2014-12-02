package com.skpw.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JacksonInject;
@Entity
@Table(name="T_PS_OutPermit")
public class TPsOutPermit extends SuperEntity{
	@Id
	private String fOutPID;	//许可证ID	char

	private Long fID;	//FID	int

	//private String fEnterID;	//企业ID	char
	@ManyToOne
	@JoinColumn(name="FEnterID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TBasEnterprise tBasEnterprise;

	private String fOutPCode;	//许可证编号	varchar

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fIssuingDate;	//发证日期	date

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fVaildDate;	//有效开始日期	date

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fEndDate;	//失效日期	date

	private String fIssueUnit;	//发证单位	varchar

	private Long fAuditMonth;	//年审月份	smallint

	private Boolean fIsSewage;	//排污类型-废水	bit

	private Boolean fIsWasteGas;	//排污类型--废气	bit

	private Boolean fIsSolidWaste;	//排污类型--固废	bit

	private Boolean fIsNoise;	//排污类型--噪声	bit

	private String fProposer;	//申请人	varchar

	@Temporal(TemporalType.DATE)
	private Date fApplyForDate;	//申请时间	date

	private String fEmpID;	//经办人	char

	@Temporal(TemporalType.DATE)
	private Date fDate;	//经办日期	date

	private String fOpinion;	//主办部门意见	varchar

	private String fIssuingOpinion;	//发证机关意见	varchar
	
	
	@OneToMany(mappedBy="tPsOutPermit", cascade=CascadeType.REMOVE)
	private List<TPsOutSewage> tPsOutSewages;

	public String getfOutPID() {
		return fOutPID;
	}

	public void setfOutPID(String fOutPID) {
		this.fOutPID = fOutPID;
	}

	public Long getfID() {
		return fID;
	}

	public void setfID(Long fID) {
		this.fID = fID;
	}

	public TBasEnterprise gettBasEnterprise() {
		return tBasEnterprise;
	}

	public void settBasEnterprise(TBasEnterprise tBasEnterprise) {
		this.tBasEnterprise = tBasEnterprise;
	}

	public String getfOutPCode() {
		return fOutPCode;
	}

	public void setfOutPCode(String fOutPCode) {
		this.fOutPCode = fOutPCode;
	}

	public Date getfIssuingDate() {
		return fIssuingDate;
	}

	public void setfIssuingDate(Date fIssuingDate) {
		this.fIssuingDate = fIssuingDate;
	}

	public Date getfVaildDate() {
		return fVaildDate;
	}

	public void setfVaildDate(Date fVaildDate) {
		this.fVaildDate = fVaildDate;
	}

	public Date getfEndDate() {
		return fEndDate;
	}

	public void setfEndDate(Date fEndDate) {
		this.fEndDate = fEndDate;
	}

	public String getfIssueUnit() {
		return fIssueUnit;
	}

	public void setfIssueUnit(String fIssueUnit) {
		this.fIssueUnit = fIssueUnit;
	}

	public Long getfAuditMonth() {
		return fAuditMonth;
	}

	public void setfAuditMonth(Long fAuditMonth) {
		this.fAuditMonth = fAuditMonth;
	}

	public Boolean getfIsSewage() {
		return fIsSewage;
	}

	public void setfIsSewage(Boolean fIsSewage) {
		this.fIsSewage = fIsSewage;
	}

	public Boolean getfIsWasteGas() {
		return fIsWasteGas;
	}

	public void setfIsWasteGas(Boolean fIsWasteGas) {
		this.fIsWasteGas = fIsWasteGas;
	}

	public Boolean getfIsSolidWaste() {
		return fIsSolidWaste;
	}

	public void setfIsSolidWaste(Boolean fIsSolidWaste) {
		this.fIsSolidWaste = fIsSolidWaste;
	}

	public Boolean getfIsNoise() {
		return fIsNoise;
	}

	public void setfIsNoise(Boolean fIsNoise) {
		this.fIsNoise = fIsNoise;
	}

	public String getfProposer() {
		return fProposer;
	}

	public void setfProposer(String fProposer) {
		this.fProposer = fProposer;
	}

	public Date getfApplyForDate() {
		return fApplyForDate;
	}

	public void setfApplyForDate(Date fApplyForDate) {
		this.fApplyForDate = fApplyForDate;
	}

	public String getfEmpID() {
		return fEmpID;
	}

	public void setfEmpID(String fEmpID) {
		this.fEmpID = fEmpID;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public String getfOpinion() {
		return fOpinion;
	}

	public void setfOpinion(String fOpinion) {
		this.fOpinion = fOpinion;
	}

	public String getfIssuingOpinion() {
		return fIssuingOpinion;
	}

	public void setfIssuingOpinion(String fIssuingOpinion) {
		this.fIssuingOpinion = fIssuingOpinion;
	}
	
	@JsonIgnore 
	public List<TPsOutSewage> gettPsOutSewages() {
		return tPsOutSewages;
	}
	
	public void settPsOutSewages(List<TPsOutSewage> tPsOutSewages) {
		this.tPsOutSewages = tPsOutSewages;
	}

	

}
