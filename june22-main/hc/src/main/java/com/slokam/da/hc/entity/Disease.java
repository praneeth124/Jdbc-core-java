package com.slokam.da.hc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Table(name="disease")
@Data
public class Disease {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Length(min=4,max=10)
	private String name;
	@Pattern(regexp="^DD[A-Z]+$")
	private String diseaseCode;
	
	
	
	
	
}
