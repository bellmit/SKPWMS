package com.skpw.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author hjy IC卡基本信息
 */

@Entity
@Table(name = "T_IC_CardInfo")
public class CardInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FCardInfoID")
	private String cardinfoid;// IC卡信息ID

	@Column(name = "FCardID")
	private String cardid;// IC卡信息ID

	@OneToOne(optional = true, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "FEnterID", referencedColumnName = "FEnterID", unique = true)
	@NotFound(action=NotFoundAction.IGNORE) 
	private TBasEnterprise enterprise;

	@Column(name = "FDate")
	private String date;// 发卡日期
	
	@Column(name = "FBeginDate")
	private String begindate;// 开始日期

	@Column(name = "FEndDate")
	private String enddate;// 结束日期

	@ManyToOne
	@JoinColumn(name = "FEmpID")
	@NotFound(action=NotFoundAction.IGNORE) 
	private TSysUserInfo userInfo;// 关联用户表

	@Column(name = "FLinkMan")
	private String FLinkMan;// 企业经办人

	@OneToOne(mappedBy = "cardInfo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action=NotFoundAction.IGNORE) 
	private ICCardControler icCardControler;
	
	@Transient
	private String ttcontrollerstr;//总量控制器信息

	public String getCardinfoid() {
		return cardinfoid;
	}

	public void setCardinfoid(String cardinfoid) {
		this.cardinfoid = cardinfoid;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public TBasEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TBasEnterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBegindate() {
		return begindate;
	}

	public void setBegindate(String begindate) {
		this.begindate = begindate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public TSysUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(TSysUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getFLinkMan() {
		return FLinkMan;
	}

	public void setFLinkMan(String fLinkMan) {
		FLinkMan = fLinkMan;
	}

	public ICCardControler getIcCardControler() {
		return icCardControler;
	}

	public void setIcCardControler(ICCardControler icCardControler) {
		this.icCardControler = icCardControler;
	}

	public String getTtcontrollerstr() {
		return ttcontrollerstr;
	}

	public void setTtcontrollerstr(String ttcontrollerstr) {
		this.ttcontrollerstr = ttcontrollerstr;
	}
	
	
}
