package com.piyushpatel2005.testharness;


import java.util.List;
import java.util.Set;

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
		
		
		Tutor newTutor = new Tutor("ABC214", "Allen Servgi", 2344);
		Student student1 = new Student("Rebecca Soni", "1-SON-2016");
		Student student2 = new Student("Zou Kai", "2-KAI-2009");
		Student student3 = new Student("Chritopher Mccay", "3-MCC-2013");
		
		session.save(student1);
		session.save(student2);
		session.save(student3);
		session.save(newTutor);
		
		newTutor.addStudentToSupervisionGorup(student1);
//		student1.allocateSupervisor(newTutor);
		
		newTutor.addStudentToSupervisionGorup(student2);
//		student2.allocateSupervisor(newTutor);
		
		newTutor.addStudentToSupervisionGorup(student3);
//		student3.allocateSupervisor(newTutor);
		
		
		
//		Set<Student> students = newTutor.getSupervisionGroup();
//		for(Student student: students) {
//			System.out.println(student);
//		}
		
		
		
		Tutor myTutor =(Tutor) session.get(Tutor.class, 1);
		Set<Student> students = myTutor.getSupervisionGroup();
		
		for(Student s: students) {
			System.out.println(s);
		}
		
		Student myStudent = (Student) session.get(Student.class, 2);
		Tutor myStudentTutor = myStudent.getSupervisor();
		System.out.println(myStudentTutor);
		
		
		
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