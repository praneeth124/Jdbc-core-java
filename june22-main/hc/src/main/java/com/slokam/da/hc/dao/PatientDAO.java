package com.slokam.da.hc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slokam.da.hc.entity.Patient;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Integer>{
     // "From Visiting v join v.appointment  a  "
	 // List<Object[]>
	 
	// " select d.name,d.speci From Visiting v join v.appointment  a join a.patient p join v.doctor d"
	// " where  p.name=?1"
	
	// List<Object[]>
	
	//  select name from Patient 
	//  List<String>
	
	//  select age from Patient 
	//  List<Integer>
	//  select name,age from Patient 
	//  List<Object[]>
	
	@Query("select d.name from Visiting v join v.appointment a"
			+ " join a.patient p join v.doctor d "
			+ " where p.name=?1 and p.isDeleted=false")
	public abstract List<String> getDoctorNamesByPatientName(String name);
	
	@Query("from Patient p where p.isDeleted=false")
	public abstract List<Patient> getAllPatients();
	
	@Query("from Patient p where p.isDeleted=false and p.id=?1 ")
	public abstract Patient getPatientById(Integer id) ;
	
	
}
