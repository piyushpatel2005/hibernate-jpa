package com.piyushpatel2005.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Person {

	@Id
	private String id;
	private String name;
	
	public Person() {}
	
	public Person(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	protected String getName() {
		return this.name;
	}
	
	public abstract void calculateReport();
}
