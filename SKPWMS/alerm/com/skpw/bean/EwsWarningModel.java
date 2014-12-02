package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author hjy 预警方式
 */
@Entity
@Table(name = "T_EWS_WarningModel")
@JsonIgnoreProperties(value = { "warningSetup" })
public class EwsWarningModel {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWModelID", nullable = false)
	private String id;

	@ManyToOne
	@JoinColumn(name = "FWSetupID")
	@NotFound(action = NotFoundAction.IGNORE)
	private WarningSetup warningSetup;// 关联预警设置

	@ManyToOne
	@JoinColumn(name = "FWarningModelID")
	@NotFound(action = NotFoundAction.IGNORE)
	private WarningModel warningModel;// 关联预警方式

	public EwsWarningModel() {

	}

	public EwsWarningModel(String id, WarningSetup warningSetup,
			WarningModel warningModel) {

		this.id = id;
		this.warningSetup = warningSetup;
		this.warningModel = warningModel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WarningSetup getWarningSetup() {
		return warningSetup;
	}

	public void setWarningSetup(WarningSetup warningSetup) {
		this.warningSetup = warningSetup;
	}

	public WarningModel getWarningModel() {
		return warningModel;
	}

	public void setWarningModel(WarningModel warningModel) {
		this.warningModel = warningModel;
	}

}
