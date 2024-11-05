package com.slokam.da.hc.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slokam.da.hc.consta.ErrorCodesEnum;
import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.exception.PatientException;
import com.slokam.da.hc.service.IPatientService;
import com.slokam.da.hc.service.impl.PatientServiceImpl;

@RestController
@RequestMapping("patient")
public class PatientController {
	@Value("${file.uploads.path}")
	private String uploadPath;
	private static Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	
	private IPatientService patientService = new PatientServiceImpl();
	@PostMapping
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) throws PatientException{
		LOGGER.debug("Entered into savePatient");
		patientService.savePatient(patient);
		LOGGER.debug("Exit from  savePatient");
		return ResponseEntity.ok(patient);
	}
	
	 @GetMapping("/doctors/{pname}")
	 public ResponseEntity<List<String>> getDoctorNamesByPatientName(@PathVariable(name="pname") String name) throws PatientException{
		    LOGGER.debug("Entered into getDoctorNamesByPatientName");
			List<String> doctorNames = patientService.getDoctorNamesByPatientName(name); 
			ResponseEntity<List<String>> re =
					new ResponseEntity<List<String>>(doctorNames,HttpStatus.OK );
			LOGGER.debug("Exit from  getDoctorNamesByPatientName");
			return re;
	 }
	 @GetMapping(value="/all/{sortBy}", 
			 produces = {MediaType.APPLICATION_XML_VALUE})
	 public ResponseEntity<List<Patient>> getAllPatients(@PathVariable String sortBy) throws PatientException {
		  LOGGER.debug("Entered into List<Patient>");
		  List<Patient>  list = patientService.getAllPatients(sortBy);
		  ResponseEntity<List<Patient>> re =
					new ResponseEntity<List<Patient>>(list,HttpStatus.OK );
			LOGGER.debug("Exit from  List<Patient>");
			return re;
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) 
	 throws PatientException{
		 LOGGER.debug("Entered into getPatientById");
		 
		 Patient patient  = patientService.getPatientById(id);
		 if(patient ==null) {
			 LOGGER.debug("Patient not found with the id "+id);
			 throw new PatientException("Not Found",null,ErrorCodesEnum.PATIEANT_NOTFOUND);
		 }
		 LOGGER.debug("Exit from  getPatientById");
		 
		 return ResponseEntity.ok(patient);
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deletePatientById(@PathVariable Integer id)
	 throws PatientException {
		 patientService.deletePatient(id);
		 return ResponseEntity.ok("Deleted");
	 }
	 
	 @PostMapping("/upload")
	 public ResponseEntity<Integer> uploadPatients(@RequestBody MultipartFile mfile)
	 throws IOException, PatientException{
		 
		 String name = mfile.getOriginalFilename();
		 name= System.currentTimeMillis()+"-"+name;
		 File file = new File(uploadPath+name);
		 mfile.transferTo(file);
		 this.patientService.uploadPatients(file);
		 return ResponseEntity.ok(1);
	 }
}
