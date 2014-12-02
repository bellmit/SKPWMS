package com.skpw.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author hjy 报警设置
 */

@Entity
@Table(name = "T_EWS_WarningSetup")
public class WarningSetup {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "FWSetupID", nullable = false)
	private String id;// 预警设置ID
	
	@Column(name="FWSetupNo")
	private String setupno;//预警编号

	@ManyToOne
	@JoinColumn(name = "FWarningItemID")
	@NotFound(action = NotFoundAction.IGNORE)
	private WarningItem warningItem;// 关联预警项目

	@ManyToOne
	@JoinColumn(name = "FPollutantID")
	@NotFound(action = NotFoundAction.IGNORE)
	private TBasPollutant pollutant; // 关联污染源因子

	@Column(name = "FThreshold")
	private String threshold;// 预警阀值

	@Column(name = "FWarningType")
	private String warningType;// 报警类型

	@OneToMany(mappedBy = "warningSetup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<EwsWarningModel> warningModels = new HashSet<EwsWarningModel>();//关联报警方式

	@OneToMany(mappedBy = "warningSetup", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<EwsWarningObject> warningObjects = new HashSet<EwsWarningObject>();//关联报警对象

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WarningItem getWarningItem() {
		return warningItem;
	}

	public void setWarningItem(WarningItem warningItem) {
		this.warningItem = warningItem;
	}

	public TBasPollutant getPollutant() {
		return pollutant;
	}

	public void setPollutant(TBasPollutant pollutant) {
		this.pollutant = pollutant;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public Set<EwsWarningModel> getWarningModels() {
		return warningModels;
	}

	public void setWarningModels(Set<EwsWarningModel> warningModels) {
		this.warningModels = warningModels;
	}

	public Set<EwsWarningObject> getWarningObjects() {
		return warningObjects;
	}

	public void setWarningObjects(Set<EwsWarningObject> warningObjects) {
		this.warningObjects = warningObjects;
	}

	public String getSetupno() {
		return setupno;
	}

	public void setSetupno(String setupno) {
		this.setupno = setupno;
	}
	

}
