package com.slokam.da.hc.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.slokam.da.hc.comp.PatientComparator;
import com.slokam.da.hc.comp.PatientIdComparator;
import com.slokam.da.hc.consta.ErrorCodesEnum;
import com.slokam.da.hc.dao.PatientDAO;
import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.exception.PatientException;
import com.slokam.da.hc.service.IPatientService;
import com.slokam.da.hc.util.DateUtil;

@Service
public class PatientServiceImpl implements IPatientService{
   private static Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class); 
	@Autowired
	private PatientDAO patientDAO;
	
	@Value("${patient.upload.line.size}")
	private String lineLength;
	
	@Override
 	public void savePatient(Patient patient)throws PatientException {
		
		LOGGER.debug("savePatient start");
		try{
			patientDAO.save(patient);
		}catch(Exception e){
			e.printStackTrace();
			throw new PatientException("DB Problem",e);
		}
		
		LOGGER.debug("savePatient end");
	}
	  
	@Override
	public Patient getPatientById(Integer id)throws PatientException {
		  LOGGER.debug("getPatientById start");
		   Patient p = null; 
		  try{
				//Optional<Patient> optional = patientDAO.findById(id);
			   p = patientDAO.getPatientById(id);
				
			}catch(Exception e){
				e.printStackTrace();
				throw new PatientException("DB Problem",e);
			}
		  LOGGER.debug("getPatientById end");
		return p;
	}
	
	@Override
    public List<String> getDoctorNamesByPatientName(String name) throws PatientException{
		 LOGGER.debug("getDoctorNamesByPatientName start");
		 List<String> doctorNames=null;
		try {
			doctorNames = patientDAO.getDoctorNamesByPatientName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PatientException("Db issue",e);
		}
		 LOGGER.debug("getDoctorNamesByPatientName end");	
		 return doctorNames;
	}
	
	@Override
		public List<Patient> getAllPatients(String sortBy) throws PatientException {
		LOGGER.debug("getAllPatients start");
		 //List<Patient> patientList = patientDAO.findAll();
		 List<Patient> patientList = patientDAO.getAllPatients();
		 
		 Comparator<Patient> comparator = PatientComparator.getPatientComparator(sortBy);
		 Collections.sort(patientList,comparator);
		 LOGGER.debug("getAllPatients end");	
		return patientList;
		}
	
	
	public Patient getMaxAgePatient(){
		 Patient p = null;
		 LOGGER.debug("getMaxAgePatient start");
		 //List<Patient> patientList = patientDAO.findAll();
		 List<Patient> patientList = patientDAO.getAllPatients();
		 for (Patient patient : patientList) {
			 if(p==null){
				 p = patient;
			 }else{
				 Date dob1  = patient.getDob();
				 Date dob2  = p.getDob();
				 if(dob1.before(dob2)){
					 p=patient;
				 }
			 }
			 
		 }
		 LOGGER.debug("getMaxAgePatient end");	
		 return p;
	}
	@Override
	public void deletePatient(Integer id) throws PatientException {
		LOGGER.debug("entered into deletePatient");
		//Optional<Patient> optional = patientDAO.findById(id);
		Patient patient = patientDAO.getPatientById(id);
		if(patient == null ) {
			LOGGER.error("There is not patient found");
			throw new PatientException("PatientNotFound",null,ErrorCodesEnum.PATIEANT_NOTFOUND);
		}else {
			patient.setIsDeleted(true);
			patientDAO.save(patient);
			LOGGER.info("patient soft deletion is completed.");
		}
		LOGGER.debug("Exit  from deletePatient");
		//-- patientDAO.deleteById(id);
	}
	
	@Override
		public List<Patient> uploadPatients(File file) throws PatientException {
		LOGGER.trace("entered into uploadPatients");
		List<Patient> list = new ArrayList<>();
		  try {
			  FileReader fr = new FileReader(file);
			  BufferedReader br = new BufferedReader(fr);
			  String str ;
			  while (  (str = br.readLine())!= null) {
				  if(str.trim().length()!=0) {
					 String values [] =str.trim().split(",");
					 if(values.length == Integer.parseInt(lineLength)) {
						 LOGGER.debug("line found.");
						 Patient patient = new Patient();
						 patient.setName(values[0]);
						 patient.setDob(DateUtil.getDateByString(values[1]));
						 patient.setPhone(Long.parseLong(values[2]));
						 patient.setIsDeleted(false);
						 list.add(patient);
						 LOGGER.debug("line added to list.");
					 }else {
						 LOGGER.warn("wrong line found.");
						 // LOG warning info
					 }
					  
				  }else {
					  LOGGER.warn("blank line found.");
					  // LOG warning info.
				  }
				 
			  }
			  
			 this.patientDAO.saveAll(list); 
			  
		} catch (FileNotFoundException e) {
			LOGGER.error("File is not available");
			throw new PatientException();
		} catch (IOException e) {
			LOGGER.error("File is not able to read");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			LOGGER.error("Date format mismatched.");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  LOGGER.trace("exit from uploadPatients");
			return list;
		}
}
