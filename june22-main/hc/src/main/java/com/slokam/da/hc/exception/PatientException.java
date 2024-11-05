package com.slokam.da.hc.exception;

import com.slokam.da.hc.consta.ErrorCodesEnum;

public class PatientException extends Exception{
    
	private ErrorCodesEnum errorCode;
    
	public ErrorCodesEnum getErrorCode() {
		return errorCode;
	}
	public PatientException(){
		
	}
	public PatientException(String message){
		super(message);
	}
	public PatientException(String message,Throwable t){
		super(message,t);
	}
	public PatientException(String message,Throwable t,ErrorCodesEnum errorcode){
		super(message,t);
		this.errorCode=errorcode;
	}
}
