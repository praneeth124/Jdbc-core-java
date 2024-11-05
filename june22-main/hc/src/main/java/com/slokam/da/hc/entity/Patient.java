package com.slokam.da.hc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="patient")
@Data
public class Patient {
	
	
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	private Date dob;
	@Column(name="phnnum")
	private Long phone;

	@Column(columnDefinition = "boolean default false")
	private Boolean isDeleted;
	
	
	
	
}
