package com.slokam.da.hc.exception;

import com.slokam.da.hc.consta.ErrorCodesEnum;

public class ApplicationError {
	private ErrorCodesEnum errorCode;
	private String description;
	private Throwable baseException;

	public ErrorCodesEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodesEnum errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Throwable getBaseException() {
		return baseException;
	}

	public void setBaseException(Throwable baseException) {
		this.baseException = baseException;
	}

}
