package com.slokam.da.hc.service;

import java.util.Date;
import java.util.List;

import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.entity.Visiting;
import com.slokam.da.hc.exception.PatientException;

public interface IVisitingService {
	public void visit(Visiting visiting) throws PatientException;
	public List<Patient> getPatientsByDate(Date from, Date to);
}
