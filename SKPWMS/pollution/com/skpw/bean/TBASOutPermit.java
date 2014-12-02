//package com.skpw.bean;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.GenericGenerator;
//
//
///*
// * 
//T_PS_OutPermit	FOutPID	许可证ID	char
//T_PS_OutPermit	FID	FID	int
//T_PS_OutPermit	FEnterID	企业ID	char
//T_PS_OutPermit	FOutPCode	许可证编号	varchar
//T_PS_OutPermit	FIssuingDate	发证日期	date
//T_PS_OutPermit	FVaildDate	有效开始日期	date
//T_PS_OutPermit	FEndDate	失效日期	date
//T_PS_OutPermit	FIssueUnit	发证单位	varchar
//T_PS_OutPermit	FAuditMonth	年审月份	smallint
//T_PS_OutPermit	FIsSewage	排污类型-废水	bit
//T_PS_OutPermit	FIsWasteGas	排污类型--废气	bit
//T_PS_OutPermit	FIsSolidWaste	排污类型--固废	bit
//T_PS_OutPermit	FIsNoise	排污类型--噪声	bit
//T_PS_OutPermit	FProposer	申请人	varchar
//T_PS_OutPermit	FApplyForDate	申请时间	date
//T_PS_OutPermit	FEmpID	经办人	char
//T_PS_OutPermit	FDate	经办日期	date
//T_PS_OutPermit	FOpinion	主办部门意见	varchar
//T_PS_OutPermit	FIssuingOpinion	发证机关意见	varchar
//*/
//
//@Entity
//@Table(name="T_PS_OutPermit")
//public class TBASOutPermit {	
//	@Id
//	@GeneratedValue(generator = "idGenerator")
//	@GenericGenerator(name = "idGenerator", strategy = "uuid")
//	@Column(name = "id", unique = true, nullable = false, length = 32)
//	private String id;
//	
//	private TBasEnterprise enterprise;//关联企业
//	
//	private String foutpcode;//许可证编号
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public TBasEnterprise getEnterprise() {
//		return enterprise;
//	}
//
//	public void setEnterprise(TBasEnterprise enterprise) {
//		this.enterprise = enterprise;
//	}
//
//	public String getFoutpcode() {
//		return foutpcode;
//	}
//
//	public void setFoutpcode(String foutpcode) {
//		this.foutpcode = foutpcode;
//	}
//	
//	
//	
//	
//
//}
