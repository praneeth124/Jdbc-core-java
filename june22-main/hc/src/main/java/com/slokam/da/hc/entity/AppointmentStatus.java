package com.slokam.da.hc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AppointmentStatus {

	@Id
	@GeneratedValue 
	private Integer id;
	private String name;
	private String description;
	

	
	
}
