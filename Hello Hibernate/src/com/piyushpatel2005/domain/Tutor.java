package com.piyushpatel2005.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Tutor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String staffId;
	private String name;
	private int salary;
	
	@OneToMany // If only this annotation is used, it will create third linking table
	@MapKey(name="enrollmentID") // required for mapping to Map collection types
	@JoinColumn(name="TUTOR_FK")
	private Map<String, Student> supervisionGroup;
	
	public Tutor() {}

	// business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new HashMap<>();
	}
	
	public void addStudentToSupervisionGorup(Student studentToAdd) {
		this.supervisionGroup.put(studentToAdd.getEnrollmentID(), studentToAdd);
	}
	
	public Map<String, Student> getSupervisionGroup() {
		return Collections.unmodifiableMap(supervisionGroup);
	}

	public String getName() {
		return this.name;
		
	}
	
	
	
	
}
