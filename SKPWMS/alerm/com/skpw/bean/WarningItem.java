package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy 预警项目
 */
@Entity
@Table(name = "T_BAS_WarningItem")
public class WarningItem {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWarningItemID", unique = true, nullable = false)
	private String id;

	@Column(name = "FWarningItemName")
	private String warningItemName;

	@Column(name = "FDescript")
	private String descript;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWarningItemName() {
		return warningItemName;
	}

	public void setWarningItemName(String warningItemName) {
		this.warningItemName = warningItemName;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
