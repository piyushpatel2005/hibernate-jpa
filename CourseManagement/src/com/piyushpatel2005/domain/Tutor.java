package com.piyushpatel2005.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tutor extends Person {
	@Column(unique=true, nullable=false)
	private String staffId;
	private int salary;

	@OneToMany(mappedBy = "supervisor", cascade={ CascadeType.PERSIST, CascadeType.REMOVE }) // If only this annotation is used, it
										// will create third linking table
	private Set<Student> supervisionGroup;

	@ManyToMany(mappedBy = "qualifiedTutors")
	private Set<Subject> subjectsQualifiedToTeach;

	public Tutor() {
		super(null);
	}

	// business constructor
	public Tutor(String staffId, String name, int salary) {
		super(name);
		this.staffId = staffId;
		this.salary = salary;
		this.supervisionGroup = new HashSet<>();
		this.subjectsQualifiedToTeach = new HashSet<>();
	}

	public void addSubjectToQualifications(Subject subject) {
		this.subjectsQualifiedToTeach.add(subject);
		subject.getQualifiedTutors().add(this);
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
		return super.getName();

	}

	public String toString() {
		return "Tutor: " + this.getName() + "( " + this.staffId + ")";
	}

	public Set<Subject> getSubjects() {
		return this.subjectsQualifiedToTeach;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((staffId == null) ? 0 : staffId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tutor other = (Tutor) obj;
		if (staffId == null) {
			if (other.staffId != null)
				return false;
		} else if (!staffId.equals(other.staffId))
			return false;
		return true;
	}

	public void createStudentAndAddToSupervisionGroup(String studentName, String enrollmentId, String street, String city, String zipOrPostcode) {
		Student student = new Student(studentName, enrollmentId, street, city, zipOrPostcode);
		this.addStudentToSupervisionGorup(student);
	}

	public void doubleSalary() {
		this.salary = this.salary * 2;
	}

	public void calculateReport() {
		System.out.println("Report for tutor " + this.getName());
	}
}
