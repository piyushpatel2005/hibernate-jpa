package com.piyushpatel2005.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ extends com.piyushpatel2005.domain.Person_ {

	public static volatile SingularAttribute<Student, String> enrollmentID;
	public static volatile SingularAttribute<Student, Address> address;
	public static volatile SingularAttribute<Student, Tutor> supervisor;

}

