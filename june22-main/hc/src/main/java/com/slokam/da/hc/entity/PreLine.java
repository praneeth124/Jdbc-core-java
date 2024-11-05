package com.slokam.da.hc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class PreLine {

	@Id
	@GeneratedValue
	private Integer id;
	private String medName;
	private Integer quantity;
	private Integer dose;
	private String  doseUnits;
	private String  usg;
	
	@ManyToOne
	@JoinColumn(name="vid")
	private Visiting visting;
	
	
}
