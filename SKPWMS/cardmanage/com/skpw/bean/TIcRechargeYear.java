package com.skpw.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name="T_IC_RechargeYear")
public class TIcRechargeYear {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String fRechargeYearID;	//IC卡年度充值明细	char

	
//	private String fRechargeDtlsID;	//IC卡充値明细ID	char
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="FRechargeDtlsID")
	private TIcRechargeDetails tIcRechargeDetails;

//	private String fRechargeID;	//IC卡充值ID	char
	@ManyToOne
	@JoinColumn(name="FRechargeID")
	private TIcRecharge tIcRecharge;

//	private String fPollutantID;	//污染因子ID	char
	@ManyToOne
	@JoinColumn(name="FPollutantID")
	private TBasPollutant tBasPollutant;

	private Double fQuantity;	//本次充值排放量	decimal

	private Long fYearID;	//年度ID	int

	@Enumerated(EnumType.ORDINAL)
	private TIcPrePaidType fPrePaid;	//充值方式	smallint

	private Double fBeforeQty;	//充值前许可排放量	decimal

	private Double fAfterQty;	//充值后许可排放量	decimal

	public String getfRechargeYearID() {
		return fRechargeYearID;
	}

	public void setfRechargeYearID(String fRechargeYearID) {
		this.fRechargeYearID = fRechargeYearID;
	}

	public TIcRechargeDetails gettIcRechargeDetails() {
		return tIcRechargeDetails;
	}

	public void settIcRechargeDetails(TIcRechargeDetails tIcRechargeDetails) {
		this.tIcRechargeDetails = tIcRechargeDetails;
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

	public Long getfYearID() {
		return fYearID;
	}

	public void setfYearID(Long fYearID) {
		this.fYearID = fYearID;
	}

	public TIcPrePaidType getfPrePaid() {
		return fPrePaid;
	}

	public void setfPrePaid(TIcPrePaidType fPrePaid) {
		this.fPrePaid = fPrePaid;
	}

	public Double getfBeforeQty() {
		return fBeforeQty;
	}

	public void setfBeforeQty(Double fBeforeQty) {
		this.fBeforeQty = fBeforeQty;
	}

	public Double getfAfterQty() {
		return fAfterQty;
	}

	public void setfAfterQty(Double fAfterQty) {
		this.fAfterQty = fAfterQty;
	}


}
