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
 * @author hjy 预警对象
 */
@Entity
@Table(name = "T_EWS_WarningObject")
@JsonIgnoreProperties(value = { "warningSetup" })
public class EwsWarningObject {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWSetupObjID", nullable = false)
	private String id;

	@ManyToOne
	@JoinColumn(name = "FWSetupID")
	@NotFound(action = NotFoundAction.IGNORE)
	private WarningSetup warningSetup;// 关联预警设置

	@ManyToOne
	@JoinColumn(name = "FWarningObjID")
	@NotFound(action = NotFoundAction.IGNORE)
	private WarningObject warningObject;// 关联报警对象

	public EwsWarningObject() {

	}

	public EwsWarningObject(String id, WarningSetup warningSetup,
			WarningObject warningObject) {

		this.id = id;
		this.warningSetup = warningSetup;
		this.warningObject = warningObject;
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

	public WarningObject getWarningObject() {
		return warningObject;
	}

	public void setWarningObject(WarningObject warningObject) {
		this.warningObject = warningObject;
	}

}
