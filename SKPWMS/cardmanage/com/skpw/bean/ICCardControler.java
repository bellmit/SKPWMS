package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author hjy IC卡与总量控制器关联
 */
@Entity
@Table(name = "T_IC_CardControler")
@JsonIgnoreProperties(value = { "cardInfo", "tcControler" })
public class ICCardControler {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name="FCardCtrlID")
	private String id;

	@OneToOne
	@JoinColumn(name = "FCardInfoID")
	@NotFound(action=NotFoundAction.IGNORE) 
	private CardInfo cardInfo;// 关联卡

	@OneToOne
	@JoinColumn(name = "FControlerID")
	@NotFound(action=NotFoundAction.IGNORE) 
	private TTcControler tcControler;// 关联总量控制器

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CardInfo getCardInfo() {
		return cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	public TTcControler getTcControler() {
		return tcControler;
	}

	public void setTcControler(TTcControler tcControler) {
		this.tcControler = tcControler;
	}

}
