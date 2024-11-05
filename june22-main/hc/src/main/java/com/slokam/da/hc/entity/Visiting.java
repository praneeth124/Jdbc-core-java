package com.slokam.da.hc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Table(name="visiting")
@Data
public class Visiting {


	@Id
	@GeneratedValue
	private Integer id;
	private Date time;
	@OneToOne
	@JoinColumn(name="did")
	private Doctor doctor;
	@OneToOne
	@JoinColumn(name="fkaid")
	private Appointment appointment;
	
	//@OneToMany(mappedBy = "visting",cascade = CascadeType.ALL)
	
	@Transient
	private List<PreLine> preLineList;
	
	
	
	
}
