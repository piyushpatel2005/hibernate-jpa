package com.piyushpatel2005.testharness;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.piyushpatel2005.domain.Student;
import com.piyushpatel2005.domain.Tutor;

public class HibernateTest {


	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
//		Tutor tutor = new Tutor("ABC23", "Ahmed Ansari", 234);
//		em.persist(tutor);
		
//		tutor.createStudentAndAddToSupervisorGroup("Marco Forbes", "1-FOR-2010", "1 The Street", "Anytown", "Atlanta");
//		tutor.createStudentAndAddToSupervisorGroup("Katheline Korea", "1-KOR-2010", "55  Town Center", "Toronto", "Ontario");
		
//		Subject sub1 = new Subject("Math", 3);
//		Subject sub2 = new Subject("CS101", 6);
//		em.persist(sub1);
//		em.persist(sub2);
//		
//		Tutor tutor2 = new Tutor("DFS234", "Paul Adams", 2342);
//		em.persist(tutor2);
//		
//		tutor.addSubjectToQualifications(sub1);
//		tutor.addSubjectToQualifications(sub2);
//		tutor2.addSubjectToQualifications(sub2);
		
//		Tutor t1 = em.find(Tutor.class, 1);
//		em.remove(t1);
//		
		
//		Student student = new Student("Dimitry Peters", "1-PET-2011");
//		em.persist(student);
		
		Student studentFromDatabase = em.find(Student.class, 1);
		System.out.println(studentFromDatabase);
		
		tx.commit();
		em.close();
	}

}
