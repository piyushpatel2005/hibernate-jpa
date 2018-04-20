package com.piyushpatel2005.testharness;


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
		
		Tutor t1 = new Tutor("ABC234", "David Banks", 2342);
		session.save(t1);
		
		Student s1 = new Student("Marco Fortes", "1-FOR-2018");
		t1.addStudentToSupervisionGorup(s1);
		
		Student s2 = new Student("Piyuhs ", "1-PAT-2018");
		t1.addStudentToSupervisionGorup(s2);
		
		Student s3 = new Student("Angie Brandy", "3-BAT-2009");
		t1.addStudentToSupervisionGorup(s3);
		
		Set<Student> allStudents = t1.getSupervisionGroup();
		System.out.println(allStudents.size());
		session.save(s1);
		session.save(s2);
		session.save(s3);
		
		
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
