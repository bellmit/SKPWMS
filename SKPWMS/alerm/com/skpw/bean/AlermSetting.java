package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hjy 报警参数属性
 */
@Entity
@Table(name = "T_SYS_ALERMSETTING")
public class AlermSetting {
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	@Column(name = "paramid")
	private String paramid;// 报警参数编号
	@Column(name = "paramname")
	private String paramname;// 报警参数名称
	@Column(name = "paramexpress")
	private String paramexpress;// 报警规则

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParamid() {
		return paramid;
	}

	public void setParamid(String paramid) {
		this.paramid = paramid;
	}

	public String getParamname() {
		return paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamexpress() {
		return paramexpress;
	}

	public void setParamexpress(String paramexpress) {
		this.paramexpress = paramexpress;
	}

}
