package com.hc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String address;
	@Past
	private Date dob;
	
	@Email(message="Please provide a valid email address")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	private String emailAddress;
	
	private List<Dependent> dependent;
	
	public Member(int id, String address, Date dob, String emailAddress, List<Dependent> dependent) {
		super();
		this.id=id;
		this.address=address;
		this.dob=dob;
		this.emailAddress=emailAddress;
		this.dependent=dependent;
	}
	
	public Member() {
		
	}

}
