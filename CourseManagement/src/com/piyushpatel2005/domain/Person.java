package com.piyushpatel2005.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PRESON_TYPE")
public abstract class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	public Person() {}
	
	public Person(String name) {
		this.name = name;
	}
	
	protected String getName() {
		return this.name;
	}
	
	public abstract void calculateReport();
}
