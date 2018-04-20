package com.piyushpatel2005.testharness;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.piyushpatel2005.domain.Student;
import com.piyushpatel2005.domain.Subject;
import com.piyushpatel2005.domain.Tutor;

public class HibernateTest {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {
		SessionFactory sf = getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Tutor tutor = new Tutor("ABC23", "Ahmed Ansari", 234);
		session.save(tutor);
		
		Student s1 = new Student("Rachel Monic", "1-MON-2008");
		session.save(s1);
		Student s2 = new Student("Missy Frank", "2-FRA-2011");
		session.save(s2);
		
		Subject sub1 = new Subject("Math", 3);
		Subject sub2 = new Subject("CS101", 6);
		session.save(sub1);
		session.save(sub2);
		
		Tutor tutor2 = new Tutor("DFS234", "Paul Adams", 2342);
		session.save(tutor2);
		
		tutor.addSubjectToQualifications(sub1);
		tutor.addSubjectToQualifications(sub2);
		tutor2.addSubjectToQualifications(sub2);
		
		tutor.addStudentToSupervisionGorup(s1);
		tutor.addStudentToSupervisionGorup(s2);
		
		
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
