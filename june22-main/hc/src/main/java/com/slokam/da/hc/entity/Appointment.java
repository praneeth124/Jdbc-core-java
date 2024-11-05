package com.slokam.da.hc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name="appointment")
@Data
public class Appointment {
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", taken=" + taken + ", reqDate=" + reqDate + ", comments=" + comments
				+ ", patient=" + patient + "]";
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	private Date taken;
	private Date reqDate;
	private String comments;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pid")
	private Patient patient;
	
	@ManyToOne()
	@JoinColumn(name="asid")
	private AppointmentStatus status;
	
	
	
	
	
}
