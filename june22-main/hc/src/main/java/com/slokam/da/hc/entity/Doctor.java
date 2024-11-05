package com.slokam.da.hc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="doctor")
@Data
public class Doctor {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String speci;
	
	
	
	
	
}
