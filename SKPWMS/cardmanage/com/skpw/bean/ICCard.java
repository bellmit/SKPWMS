package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy IC卡信息
 */
@Entity
@Table(name = "T_IC_Card")
public class ICCard {

	@Id
	@Column(name = "FCardID")
	private String id;//

	@Column(name = "FCardNo")
	private String FCardNo;// IC卡编号

	@Column(name = "FCardPhyNo")
	private String cardPhyNo;// IC卡物理编号

	@Column(name = "FEncryptInfo")
	private String encryptInfo;// IC卡加密信息

	@Column(name = "FStatus")
	private Integer status;// IC卡状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFCardNo() {
		return FCardNo;
	}

	public void setFCardNo(String fCardNo) {
		FCardNo = fCardNo;
	}

	public String getCardPhyNo() {
		return cardPhyNo;
	}

	public void setCardPhyNo(String cardPhyNo) {
		this.cardPhyNo = cardPhyNo;
	}

	public String getEncryptInfo() {
		return encryptInfo;
	}

	public void setEncryptInfo(String encryptInfo) {
		this.encryptInfo = encryptInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
