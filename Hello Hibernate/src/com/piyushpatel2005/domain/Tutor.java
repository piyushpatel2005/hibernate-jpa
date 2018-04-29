package com.piyushpatel2005.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Tutor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private String staffId;
	private String name;
	private int salary;

	@OneToMany(mappedBy = "supervisor", cascade={ CascadeType.PERSIST, CascadeType.REMOVE }) // If only this annotation is used, it
										// will create third linking table
	private Set<Student> supervisionGroup;

	@ManyToMany(mappedBy = "qualifiedTutors")
	private Set<Subject> subjectsQualifiedToTeach;

	public Tutor() {
	}

	// business constructor
	public Tutor(String staffId, String name, int salary) {
		super();
		this.staffId = staffId;
		this.name = name;
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
		return this.name;

	}

	public String toString() {
		return "Tutor: " + this.name + "( " + this.staffId + ")";
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

	public void createStudentAndAddToSupervisorGroup(String studentName, String enrollmentId, String street, String city, String zipOrPostcode) {
		Student student = new Student(studentName, enrollmentId, street, city, zipOrPostcode);
		this.addStudentToSupervisionGorup(student);
	}

}
