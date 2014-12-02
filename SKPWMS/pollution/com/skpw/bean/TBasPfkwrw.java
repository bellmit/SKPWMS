package com.skpw.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "T_Bas_Pfkxx")
public class TBasPfkwrw {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	private String id;
	
	@Column(name = "wrybh")
	private String wrybh;

	@Column(name = "pfkbh")
	private String pfkbh;

	@Column(name = "wrw")
	private String wrw;

	@Column(name = "ndbjMin")
	private String ndbjMin;

	@Column(name = "yyStyle")
	private String yyStyle;

	@Column(name = "ndbjMax")
	private String ndbjMax;

	@Column(name = "pfbz")
	private String pfbz;

	@Column(name = "pfbzz")
	private String pfbzz;

	@Column(name = "pflBjzYear")
	private String pflBjzYear;

	@Column(name = "pflBjzQuarter")
	private String pflBjzQuarter;

	@Column(name = "pflBjzMonth")
	private String pflBjzMonth;

	@Column(name = "pflBjzPerYear")
	private String pflBjzPerYear;

	@Column(name = "pflBjzPerQuarter")
	private String pflBjzPerQuarter;

	@Column(name = "pflBjzPerMonth")
	private String pflBjzPerMonth;

	@Column(name = "status")
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWrybh() {
		return wrybh;
	}

	public void setWrybh(String wrybh) {
		this.wrybh = wrybh;
	}

	public String getPfkbh() {
		return pfkbh;
	}

	public void setPfkbh(String pfkbh) {
		this.pfkbh = pfkbh;
	}

	public String getWrw() {
		return wrw;
	}

	public void setWrw(String wrw) {
		this.wrw = wrw;
	}

	public String getNdbjMin() {
		return ndbjMin;
	}

	public void setNdbjMin(String ndbjMin) {
		this.ndbjMin = ndbjMin;
	}

	public String getYyStyle() {
		return yyStyle;
	}

	public void setYyStyle(String yyStyle) {
		this.yyStyle = yyStyle;
	}

	public String getNdbjMax() {
		return ndbjMax;
	}

	public void setNdbjMax(String ndbjMax) {
		this.ndbjMax = ndbjMax;
	}

	public String getPfbz() {
		return pfbz;
	}

	public void setPfbz(String pfbz) {
		this.pfbz = pfbz;
	}

	public String getPfbzz() {
		return pfbzz;
	}

	public void setPfbzz(String pfbzz) {
		this.pfbzz = pfbzz;
	}

	public String getPflBjzYear() {
		return pflBjzYear;
	}

	public void setPflBjzYear(String pflBjzYear) {
		this.pflBjzYear = pflBjzYear;
	}

	public String getPflBjzQuarter() {
		return pflBjzQuarter;
	}

	public void setPflBjzQuarter(String pflBjzQuarter) {
		this.pflBjzQuarter = pflBjzQuarter;
	}

	public String getPflBjzMonth() {
		return pflBjzMonth;
	}

	public void setPflBjzMonth(String pflBjzMonth) {
		this.pflBjzMonth = pflBjzMonth;
	}

	public String getPflBjzPerYear() {
		return pflBjzPerYear;
	}

	public void setPflBjzPerYear(String pflBjzPerYear) {
		this.pflBjzPerYear = pflBjzPerYear;
	}

	public String getPflBjzPerQuarter() {
		return pflBjzPerQuarter;
	}

	public void setPflBjzPerQuarter(String pflBjzPerQuarter) {
		this.pflBjzPerQuarter = pflBjzPerQuarter;
	}

	public String getPflBjzPerMonth() {
		return pflBjzPerMonth;
	}

	public void setPflBjzPerMonth(String pflBjzPerMonth) {
		this.pflBjzPerMonth = pflBjzPerMonth;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
