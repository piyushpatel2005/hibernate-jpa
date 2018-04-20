package com.piyushpatel2005.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	@OneToMany // If only this annotation is used, it will create third linking table
//	@OrderBy("name")
	@OrderColumn(name="student_order")
	@JoinColumn(name="TUTOR_FK")
	private List<Student> supervisionGroup;
	
	public Tutor() {}

	// business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
		this.salary = salary;
		this.supervisionGroup = new ArrayList<>();
	}
	
	public void addStudentToSupervisionGorup(Student studentToAdd) {
		this.supervisionGroup.add(studentToAdd);
	}
	
	public List<Student> getSupervisionGroup() {
		return Collections.unmodifiableList(supervisionGroup);
	}

	public String getName() {
		return this.name;
		
	}
	
	
	
	
}
