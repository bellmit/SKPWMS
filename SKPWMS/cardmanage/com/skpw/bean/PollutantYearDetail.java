package com.skpw.bean;

/**
 * @author hjy 总量控制器年度许可排排放量详情
 */
public class PollutantYearDetail {

	private String FOutPID;// 排污许可证ID

	private String FOutPCode;// 排污许可证编码

	private String FEnterName;// 企业名称

	private String FPollutantName;// 污染源因子名称

	private String FYearID;// 年份

	private float FDischarge;// 年度许可排放量

	private float FUpperLimit;// 排放浓度限值

	public PollutantYearDetail() {

	}

	public PollutantYearDetail(String fPollutantName, String fYearID,
			float fDischarge, float fUpperLimit) {

		FPollutantName = fPollutantName;
		FYearID = fYearID;
		FDischarge = fDischarge;
		FUpperLimit = fUpperLimit;
	}

	public String getFOutPID() {
		return FOutPID;
	}

	public void setFOutPID(String fOutPID) {
		FOutPID = fOutPID;
	}

	public String getFOutPCode() {
		return FOutPCode;
	}

	public void setFOutPCode(String fOutPCode) {
		FOutPCode = fOutPCode;
	}

	public String getFEnterName() {
		return FEnterName;
	}

	public void setFEnterName(String fEnterName) {
		FEnterName = fEnterName;
	}

	public String getFYearID() {
		return FYearID;
	}

	public void setFYearID(String fYearID) {
		FYearID = fYearID;
	}

	public String getFPollutantName() {
		return FPollutantName;
	}

	public void setFPollutantName(String fPollutantName) {
		FPollutantName = fPollutantName;
	}

	public float getFDischarge() {
		return FDischarge;
	}

	public void setFDischarge(float fDischarge) {
		FDischarge = fDischarge;
	}

	public float getFUpperLimit() {
		return FUpperLimit;
	}

	public void setFUpperLimit(float fUpperLimit) {
		FUpperLimit = fUpperLimit;
	}

}
