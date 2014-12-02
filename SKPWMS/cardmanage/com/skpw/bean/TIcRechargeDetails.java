package com.skpw.bean;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_IC_RechargeDetails")
public class TIcRechargeDetails {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fRechargeDtlsID;	//IC卡充值明细ID	char
	
//	private String fRechargeID;	//IC卡充值ID	char
	@ManyToOne
	@JoinColumn(name="FRechargeID")
	private TIcRecharge tIcRecharge;

//	private Long fPollutantID;	//污染物ID	int
	@ManyToOne
	@JoinColumn(name="FPollutantID")
	private TBasPollutant tBasPollutant;

	private Double fQuantity;	//充值排放量	decimal

//	public static enum paidType{
//		按年度充值,按周期充值
//	}
	@Enumerated(EnumType.ORDINAL)
	private TIcPrePaidType fPrePaid;	//充值方式	smallint

	private Long fYearID;	//年ID	int

	public String getfRechargeDtlsID() {
		return fRechargeDtlsID;
	}

	public void setfRechargeDtlsID(String fRechargeDtlsID) {
		this.fRechargeDtlsID = fRechargeDtlsID;
	}

	public TIcRecharge gettIcRecharge() {
		return tIcRecharge;
	}

	public void settIcRecharge(TIcRecharge tIcRecharge) {
		this.tIcRecharge = tIcRecharge;
	}

	public TBasPollutant gettBasPollutant() {
		return tBasPollutant;
	}

	public void settBasPollutant(TBasPollutant tBasPollutant) {
		this.tBasPollutant = tBasPollutant;
	}

	public Double getfQuantity() {
		return fQuantity;
	}

	public void setfQuantity(Double fQuantity) {
		this.fQuantity = fQuantity;
	}

	public TIcPrePaidType getfPrePaid() {
		return fPrePaid;
	}

	public void setfPrePaid(TIcPrePaidType fPrePaid) {
		this.fPrePaid = fPrePaid;
	}

	public Long getfYearID() {
		return fYearID;
	}

	public void setfYearID(Long fYearID) {
		this.fYearID = fYearID;
	}

	
}
