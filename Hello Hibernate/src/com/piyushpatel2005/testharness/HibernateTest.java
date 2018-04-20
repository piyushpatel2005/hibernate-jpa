package com.piyushpatel2005.testharness;


import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.piyushpatel2005.domain.Student;
import com.piyushpatel2005.domain.Subject;
import com.piyushpatel2005.domain.Tutor;

public class HibernateTest {


	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Tutor tutor = new Tutor("ABC23", "Ahmed Ansari", 234);
		em.persist(tutor);
		
		Student s1 = new Student("Rachel Monic", "1-MON-2008");
		em.persist(s1);
		Student s2 = new Student("Missy Frank", "2-FRA-2011");
		em.persist(s2);
		
		Subject sub1 = new Subject("Math", 3);
		Subject sub2 = new Subject("CS101", 6);
		em.persist(sub1);
		em.persist(sub2);
		
		Tutor tutor2 = new Tutor("DFS234", "Paul Adams", 2342);
		em.persist(tutor2);
		
		tutor.addSubjectToQualifications(sub1);
		tutor.addSubjectToQualifications(sub2);
		tutor2.addSubjectToQualifications(sub2);
		
		tutor.addStudentToSupervisionGorup(s1);
		tutor.addStudentToSupervisionGorup(s2);
		
		
		
		
		tx.commit();
		em.close();
	}

}
