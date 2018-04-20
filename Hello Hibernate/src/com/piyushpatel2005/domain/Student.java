package com.piyushpatel2005.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Represents a Student enrolled in the college management
 * system (CMS)
 */

@Entity
@Table(name = "TBL_STUDENT")
public class Student
{
	
	// Now, using property access using setters and getters, so annotations are on getters
	private int id;
    private String enrollmentID;
    private String name;
    private String tutorName; // This will become a class soon
    
    @Column(name="NUM_COURSES")
    private Integer numberOfCourses; // If it was int type, Hibernate would automatically make this column not null as int cannot have null value
    
    /**
     * Required by Hibernate when we have other constructors
     */
    public Student() 
    {
    	
    }
    
    /**
     * Initialises a student with a particular tutor
     */
    public Student(String name, String tutorName)
    {
    	this.name = name;
    	this.tutorName = tutorName;
    }
    
    
    /**
     * Initialises a student with no pre set tutor
     */
    public Student(String name)
    {
    	this.name = name;
    	this.tutorName = null;
    	this.numberOfCourses = 7;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getTutorName() {
		return tutorName;
	}

	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}
	
	public Integer getNumberOfCourses() {
		return numberOfCourses;
	}

	public void setNumberOfCourses(Integer numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Transient
	public double getAverageScore() {
		// We don't want this in table.
		return 0;
	}
}
