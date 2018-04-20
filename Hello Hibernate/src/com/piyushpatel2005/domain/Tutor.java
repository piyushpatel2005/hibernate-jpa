package com.piyushpatel2005.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Tutor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String staffId;
	private String name;
	private int salary;
	
	@OneToMany
	@JoinColumn(name="TUTOR_FK")
	private Set<Student> supervisionGroup;
	
	public Tutor() {}

	// business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new HashSet<>();
	}
	
	public void addStudentToSupervisionGorup(Student studentToAdd) {
		this.supervisionGroup.add(studentToAdd);
	}
	
	public Set<Student> getSupervisionGroup() {
		return Collections.unmodifiableSet(supervisionGroup);
	}

	public String getName() {
		return this.name;
		
	}
	
	
	
	
}
