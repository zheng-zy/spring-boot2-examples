package com.zzy.feign;

import java.io.Serializable;


public class F1000Mapper implements Serializable {

	private static final long serialVersionUID = 1555373070339982272L;
	
	private String customerId;
	private String companyCode;
	private String startTime;
	private String endTime;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
