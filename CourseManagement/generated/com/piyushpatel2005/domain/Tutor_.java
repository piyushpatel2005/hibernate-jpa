package com.piyushpatel2005.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tutor.class)
public abstract class Tutor_ extends com.piyushpatel2005.domain.Person_ {

	public static volatile SetAttribute<Tutor, Student> supervisionGroup;
	public static volatile SetAttribute<Tutor, Subject> subjectsQualifiedToTeach;
	public static volatile SingularAttribute<Tutor, Integer> salary;
	public static volatile SingularAttribute<Tutor, String> staffId;

}

