package com.slokam.da.hc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.entity.Visiting;
import com.slokam.da.hc.exception.PatientException;
import com.slokam.da.hc.service.IVisitingService;

@RestController
@RequestMapping("visiting")
public class VisitingController {
	private static Logger LOGGER = LoggerFactory.getLogger(VisitingController.class);
	@Autowired
	private IVisitingService visitingService;
	
	@PostMapping() 
	public ResponseEntity<String> visit(@RequestBody Visiting visiting) throws PatientException{
		LOGGER.debug("Entered into visit");
		ResponseEntity<String> re= null;
		if(visiting!=null){
			LOGGER.debug("visiting data"+visiting);
			visitingService.visit(visiting);
			re = new ResponseEntity<String>(HttpStatus.CREATED);
		}else{
			re = new ResponseEntity<String>("null visiting",HttpStatus.BAD_REQUEST);
		}
		LOGGER.debug("Exit from visit");
		return re;
	}
	@GetMapping(value="/{from}/{to}")
	public ResponseEntity<List<Patient>> getPatientsByDate
	  ( @PathVariable("from") String from, @PathVariable("to")  String to){
		List<Patient> patientList =null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date datefrom = format.parse(from);
			Date dateto = format.parse(to);
			patientList =  visitingService.getPatientsByDate(datefrom,dateto);
			 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ResponseEntity.ok(patientList);
	}
}
