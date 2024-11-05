package com.slokam.da.hc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.da.hc.entity.Appointment;
import com.slokam.da.hc.exception.PatientException;
import com.slokam.da.hc.service.IAppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
	private static Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	@Autowired
	private IAppointmentService appointmentService;
	// syasfasdfasdf
	@PostMapping()
	public ResponseEntity<String> createAppointment
	  (@RequestBody Appointment appointment) throws PatientException{
		LOGGER.trace("Entered into createAppointment");
		 ResponseEntity<String> re =null;
		if(appointment!=null){
			LOGGER.debug("Appointment::"+appointment);
			appointmentService.createAppointment(appointment);
		    re = new ResponseEntity<String>("Successfully inserted",HttpStatus.OK);
		}else{
			re = new ResponseEntity<String>("Data is not present",HttpStatus.BAD_REQUEST);
		}
			
		LOGGER.trace("Exit from createAppointment");
		return re;
	}
	
	@Operation(summary = "Status update:<br>"
			+ "  controller method<br>"
			+ "    id, asid<br>"
			+ "  interface method<br>"
			+ "  implementation.<br>"
			+ "     getting appointment object<br>"
			+ "     setting status to appointment.<br>"
			+ "     save<br>"
			+ "<br>"
			+ "Logger<br>"
			+ "exception<br>"
			+ "swagger doc<br>"
			+ "validation.")

	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "updateStatus", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Appointment.class)) }),
	  @ApiResponse(responseCode = "400", description = "Data invalid", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Appointment not found", 
	    content = @Content) })
	@PatchMapping("/{aid}/{asid}")
	public ResponseEntity<Appointment> updateStatus(
			@PathVariable("aid") Integer aid ,
			@Parameter(description = " 1	Created	Created<br>"
					+ "2	Postponed	Postponed<br>"
					+ "3	Canceled	Canceled<br>"
					+ "4	Visited	Visited<br>"
					+ "") 
	        @PathVariable("asid") 
			
			 Integer asid) throws PatientException{
		LOGGER.info("Entered into updateStatus");

		
        if( !(asid>=1 && asid<=4) ) {
        	LOGGER.warn("Invalid appointment status provided");
        	throw new PatientException("Invalid Appointment status");
        }
		Appointment ap = appointmentService.updateStatus(aid,  asid);
		LOGGER.trace("Exit from updateStatus");
		return ResponseEntity.ok(ap);
	}
}
