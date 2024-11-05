package com.slokam.da.hc.consta;

public enum ErrorCodesEnum {

	PATIEANT_NOTFOUND("P404"),
	DOCTOR_NOTFOUND("D404"),
	APPOINTMENT_NOTFOUND("A404") ;
	String value="";
	 ErrorCodesEnum(String str) {
		this.value = str;
	}
}
