package com.piyushpatel2005.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject.class)
public abstract class Subject_ {

	public static volatile SingularAttribute<Subject, Integer> id;
	public static volatile SingularAttribute<Subject, Integer> numberOfSemesters;
	public static volatile SetAttribute<Subject, Tutor> qualifiedTutors;
	public static volatile SingularAttribute<Subject, String> subjectName;

}

