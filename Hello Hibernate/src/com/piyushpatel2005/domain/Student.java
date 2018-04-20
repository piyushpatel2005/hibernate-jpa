package com.piyushpatel2005.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Represents a Student enrolled in the college management
 * system (CMS)
 */

@Entity
public class Student
{
	
	// Now, using property access using setters and getters, so annotations are on getters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
    private String enrollmentID;
    private String name;
    
    @ManyToOne
    @JoinColumn(name="TUTOR_FK")
    private Tutor supervisor; 

    /**
     * Required by Hibernate when we have other constructors
     */
    public Student() 
    {
    	
    }
    
    /**
     * Initialises a student with a particular tutor
     */
    public Student(String name, Tutor tutor)
    {
    	this.name = name;
    	this.supervisor = tutor;
    }
    
    
    /**
     * Initialises a student with no pre set tutor
     */
    public Student(String name)
    {
    	this.name = name;
    	this.supervisor = null;
    }
    

    public int getId() {
    	return this.id;
    }
    
    public double calculateGradePointAverage()
    {
    	// some complex business logic!
    	// we won't need this method on the course, BUT it is import
    	// to remember that classes aren't just get/set pairs - we expect
    	// business logic in here as well.
    	return 0;
    }
    
    public String toString() {
    	return this.name;
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
	}

	public String getSupervisorName() {
		return this.supervisor.getName();
	}

	public Tutor getSupervisor() {
		return this.supervisor;
	}
}
