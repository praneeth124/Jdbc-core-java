package com.slokam.da.hc.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.slokam.da.hc.entity.Visiting;

@Repository
public interface VisitingDAO extends JpaRepository<Visiting, Integer> {
	 @Query("select p.id,p.name,p.dob from Visiting v join"
	 		+ " v.appointment a join a.patient p where v.time between ?1 and ?2")
	 public List<Object[]> getData(Date from , Date to ); 
}
