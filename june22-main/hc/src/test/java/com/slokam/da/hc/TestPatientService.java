package com.slokam.da.hc;

import  org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.slokam.da.hc.dao.PatientDAO;
import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.service.IPatientService;
import com.slokam.da.hc.service.impl.PatientServiceImpl;
import com.slokam.da.hc.util.DateUtil;



@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestPatientService {

	@Mock
	private PatientDAO patientDao;
	
	@InjectMocks
	private IPatientService patientService = new PatientServiceImpl();
	
	@Test
	public void test() throws Exception{
		
		Patient p3 = new Patient();
		p3.setId(5);
		p3.setName("C");
		p3.setIsDeleted(false);
		p3.setPhone(8885434L);
		p3.setDob(DateUtil.getDateByString("1999-11-12"));
		
		
		
		Mockito.when(patientDao.getAllPatients()).
		      thenReturn(prepareDummyPatients());
		Patient p = patientService.getMaxAgePatient();
		Assert.assertEquals(p3, p);
		
	}
	
	public List<Patient> prepareDummyPatients() throws Exception {
		List<Patient> list  = new ArrayList<>();
		Patient p = new Patient();
		p.setId(2);
		p.setName("A");
		p.setIsDeleted(false);
		p.setPhone(34535434L);
		p.setDob(DateUtil.getDateByString("2000-12-12"));
		
		list.add(p);
		
		Patient p2 = new Patient();
		p2.setId(4);
		p2.setName("B");
		p2.setIsDeleted(false);
		p2.setPhone(3435434L);
		p2.setDob(DateUtil.getDateByString("2003-11-12"));
		
		list.add(p2);
		
		Patient p3 = new Patient();
		p3.setId(5);
		p3.setName("C");
		p3.setIsDeleted(false);
		p3.setPhone(8885434L);
		p3.setDob(DateUtil.getDateByString("1999-11-12"));
		list.add(p3);
		
		
		Patient p4 = new Patient();
		p4.setId(6);
		p4.setName("D");
		p4.setIsDeleted(false);
		p4.setPhone(8885434L);
		p4.setDob(DateUtil.getDateByString("2009-11-23"));
		list.add(p4);
		
		Patient p5 = new Patient();
		p5.setId(7);
		p5.setName("E");
		p5.setIsDeleted(false);
		p5.setPhone(8885434L);
		p5.setDob(DateUtil.getDateByString("2022-11-23"));
		list.add(p5);
		return list;
	}
}
