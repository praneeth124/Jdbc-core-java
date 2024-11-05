package com.slokam.da.hc.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.slokam.da.hc.consta.ErrorCodesEnum;

@RestControllerAdvice

public class PatientExceptionHandler {

	private Logger LOGGER = LoggerFactory.getLogger(PatientExceptionHandler.class);
	
	@ExceptionHandler(PatientException.class)
	public ResponseEntity<ApplicationError> handlePatientException(PatientException pe){
		LOGGER.debug("entered into handlePatientException");
		ResponseEntity<ApplicationError> re =null;
		ApplicationError error = new ApplicationError();
		error.setBaseException(pe);
		error.setDescription(pe.getMessage());
		error.setErrorCode( pe.getErrorCode());
		if(pe.getErrorCode() == ErrorCodesEnum.PATIEANT_NOTFOUND) {
			 re = new ResponseEntity<ApplicationError>(error,HttpStatus.NOT_FOUND );
		}else
		{
			 re = new ResponseEntity<ApplicationError>(error, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		LOGGER.debug("exit from handlePatientException");
		return re;
	}
	
	
}
