package com.slokam.da.hc.service.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slokam.da.hc.dao.PreLineDAO;
import com.slokam.da.hc.dao.VisitingDAO;
import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.entity.PreLine;
import com.slokam.da.hc.entity.Visiting;
import com.slokam.da.hc.exception.PatientException;
import com.slokam.da.hc.service.IVisitingService;

@Service
public class VisitingServiceImpl implements IVisitingService{

	private static Logger LOOGER = LoggerFactory.getLogger(VisitingServiceImpl.class);
	@Autowired
	private VisitingDAO visitingDao;
	
	@Autowired
	private PreLineDAO preLineDao;
	
	@Override
	@Transactional
	public void visit(Visiting visiting) throws PatientException {
		LOOGER.debug("Entered into visit method.");
		try {
			if(visiting!=null){
				LOOGER.debug("visiting details::"+visiting);
				visiting.setTime(new Date());
				/*
				List<PreLine> preLines = visiting.getPreLineList();
				for (PreLine preLine : preLines) {
					preLine.setVisting(visiting);
				}
				*/
				
			  visitingDao.save(visiting);
			 
			  List<PreLine> preLines = visiting.getPreLineList();
			  
			  for (PreLine preLine : preLines) {
				  preLine.setVisting(visiting);
			  }
			  
			  preLineDao.saveAll(preLines);
			  
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PatientException("DB error", e);
		}
		LOOGER.debug("Exit from visit method.");
	}
	
   public List<Patient> getPatientsByDate(Date from, Date to){
	   List<Patient> patients =new ArrayList();
	   List<Object[]> vlaues =  visitingDao.getData(from, to);
	   
	   for (Object[] object : vlaues) {
		Patient p = new Patient();
		p.setId( (Integer) object[0]);
		p.setName(object[1].toString());
		p.setDob( (Date)object[2]);
		patients.add(p);
	  }
	   
	   return patients;
   }
	
}
