package com.piyushpatel2005.testharness;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.piyushpatel2005.domain.Student;
import com.piyushpatel2005.domain.Tutor;

public class HibernateTest {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {

		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		
		// Create a new tutor and a student 
		// Make student be supervised by a tutor
//		Student myStudent = new Student("Alister Crook");
//		Tutor newTutor  = new Tutor("XYZ123", "John Ahmed", 30000);
//		
//		myStudent.allocateSupervisor(newTutor);
//		
//		// print out supervisor for this tutor
//		System.out.println(myStudent.getSupervisorName());
//		session.save(myStudent);
//		session.save(newTutor);
		
		Student foundStudent = (Student) session.get(Student.class, 1);
		System.out.println(foundStudent);
		
//		Tutor supervisor = foundStudent.getSupervisor();
//		System.out.println(supervisor.getName());
		
		Tutor newSupervisor = (Tutor)session.get(Tutor.class, 2);
		foundStudent.allocateSupervisor(newSupervisor);
		
		tx.commit();
		session.close();
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}
		return sessionFactory;
	}
}
