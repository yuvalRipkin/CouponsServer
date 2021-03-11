package com.yuval.coupons.dto;

public class ErrorBean {

	private int status;
	private String errorName;
	private String errorMessage;

	public ErrorBean(int status, String errorName, String errorMessage) {
		super();
		this.status = status;
		this.errorName = errorName;
		this.errorMessage = errorMessage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
