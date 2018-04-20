package com.piyushpatel2005.testharness;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.piyushpatel2005.domain.Student;

public class HibernateTest {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		Student testStudent = new Student("Jessica Ennis", "Tony Minnichiello");
		System.out.println(testStudent + " has a grade point average of " + testStudent.calculateGradePointAverage());

		// save the student to the database
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		session.save(testStudent);
		
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
