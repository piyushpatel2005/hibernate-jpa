package com.piyushpatel2005.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Tutor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String staffId;
	private String name;
	private int salary;
	
	@OneToMany (mappedBy = "supervisor") // If only this annotation is used, it will create third linking table
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
		studentToAdd.allocateSupervisor(this);
	}
	
	public Set<Student> getSupervisionGroup() {
		return Collections.unmodifiableSet(supervisionGroup);
	}
	
	public Set<Student> getModifiableSupervisionGroup() {
		return this.supervisionGroup;
	}

	public String getName() {
		return this.name;
		
	}
	
	public String toString() {
		return "Tutor: " + this.name  + "( " + this.staffId + ")";
	}
	
	
}
