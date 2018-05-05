package com.piyushpatel2005.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Represents a Student enrolled in the college management system (CMS)
 */

@Entity
public class Student {

	// Now, using property access using setters and getters, so annotations are
	// on getters
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private String enrollmentID;
	private String name;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TUTOR_FK")
	private Tutor supervisor;

	@Embedded
	private Address address;

	/**
	 * Required by Hibernate when we have other constructors
	 */
	public Student() {

	}

	/**
	 * Initialises a student with no pre set tutor
	 */
	public Student(String name, String enrollmentID, String street, String city, String zipOrPostcode) {
		this.name = name;
		this.enrollmentID = enrollmentID;
		this.supervisor = null;
		this.address = new Address(street, city, zipOrPostcode);
	}
	
	public Student(String name, String enrollmentId) {
		this.name = name;
		this.enrollmentID = enrollmentId;
		this.address = null;
	}

	public int getId() {
		return this.id;
	}

	public double calculateGradePointAverage() {
		// some complex business logic!
		// we won't need this method on the course, BUT it is import
		// to remember that classes aren't just get/set pairs - we expect
		// business logic in here as well.
		return 0;
	}

	public String toString() {
		return this.name + " lives at " + this.address;
	}

	public String getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(String enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}

	public void setId(int id) {
		this.id = id;
	}

	@Transient
	public double getAverageScore() {
		// We don't want this in table.
		return 0;
	}

	public void allocateSupervisor(Tutor newTutor) {
		this.supervisor = newTutor;
		newTutor.getModifiableSupervisionGroup().add(this);
	}

	public String getSupervisorName() {
		return this.supervisor.getName();
	}

	public Tutor getSupervisor() {
		return this.supervisor;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enrollmentID == null) ? 0 : enrollmentID.hashCode());
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
		Student other = (Student) obj;
		if (enrollmentID == null) {
			if (other.enrollmentID != null)
				return false;
		} else if (!enrollmentID.equals(other.enrollmentID))
			return false;
		return true;
	}

}