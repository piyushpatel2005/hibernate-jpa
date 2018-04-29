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
//		
//		tutor.createStudentAndAddToSupervisorGroup("Marco Forbes", "1-FOR-2010", "1 The Street", "Anytown", "Atlanta");
//		tutor.createStudentAndAddToSupervisorGroup("Katheline Korea", "1-KOR-2010", "55  Town Center", "Toronto", "Ontario");
			
		Student student = em.find(Student.class, 2);
		System.out.println(student);
		tx.commit();
		em.close();
	}

}
