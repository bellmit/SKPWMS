package com.skpw.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="T_IC_Recharge")
public class TIcRecharge extends SuperEntity{

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fRechargeID ;	//IC卡充值ID	char
	
	private String fRechargeNo;//充值单号	varchar
	
	@Column(insertable=false)
	private Long fSn;//FSN	充值流水号	int

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fDate;	//充值日期	date

//	private String fCardInfoID;	//IC卡信息ID	char
	@ManyToOne
	@JoinColumn(name="FCardInfoID")
	@NotFound(action=NotFoundAction.IGNORE)
	private CardInfo cardInfo;

//	private String fEnterID;	//污染源企业ID	char
	@ManyToOne
	@JoinColumn(name="FEnterID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TBasEnterprise tBasEnterprise;

//	private String fEmpID;	//经办人ID	char
	@ManyToOne
	@JoinColumn(name="fEmpID")
	private TSysUserInfo fEmp;

//	private String fOutPID;	//排污许可证ID	char
	@ManyToOne
	@JoinColumn(name="FOutPID")
	@NotFound(action=NotFoundAction.IGNORE)
	private TPsOutPermit tPsOutPermit;

	private String fLinkMan;	//企业办理人	varchar

	public String getfRechargeID() {
		return fRechargeID;
	}

	public void setfRechargeID(String fRechargeID) {
		this.fRechargeID = fRechargeID;
	}

	public String getfRechargeNo() {
		return fRechargeNo;
	}

	public void setfRechargeNo(String fRechargeNo) {
		this.fRechargeNo = fRechargeNo;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	public TBasEnterprise gettBasEnterprise() {
		return tBasEnterprise;
	}

	public void settBasEnterprise(TBasEnterprise tBasEnterprise) {
		this.tBasEnterprise = tBasEnterprise;
	}

	public TSysUserInfo getfEmp() {
		return fEmp;
	}

	public void setfEmp(TSysUserInfo fEmp) {
		this.fEmp = fEmp;
	}

	public TPsOutPermit gettPsOutPermit() {
		return tPsOutPermit;
	}

	public void settPsOutPermit(TPsOutPermit tPsOutPermit) {
		this.tPsOutPermit = tPsOutPermit;
	}

	public String getfLinkMan() {
		return fLinkMan;
	}

	public void setfLinkMan(String fLinkMan) {
		this.fLinkMan = fLinkMan;
	}

	public Long getfSn() {
		return fSn;
	}

	public void setfSn(Long fSn) {
		this.fSn = fSn;
	}


}
