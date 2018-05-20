package com.piyushpatel2005.testharness;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.piyushpatel2005.domain.Student;
import com.piyushpatel2005.domain.Subject;
import com.piyushpatel2005.domain.Tutor;

public class HibernateTestHarness 
{
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabaseConfig");

	public static void main(String[] args)
	{		
		setUpData();
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// let's do some queries!
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
		
		// from clause
		Root<Student> root = criteria.from(Student.class);
		
		criteria.where(builder.equal(root.get("name"), "Marco Fortes"));
		
		Query q = em.createQuery(criteria);
		List<Student> results = q.getResultList();
		
		for(Student next: results) {
			System.out.println(next);
		}
		
		tx.commit();
		em.close();
	}
	
	public static void setUpData()
	{
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		// Some subjects
		Subject mathematics = new Subject("Mathematics", 2);
		Subject science = new Subject("Science", 2);
		Subject history = new Subject("History", 3);
		em.persist(mathematics);
		em.persist(science);
		em.persist(history);

		// This tutor will be very busy, with lots of students
		Tutor t1 = new Tutor("ABC123", "David Banks", 1000);
		t1.addSubjectToQualifications(mathematics);
		t1.addSubjectToQualifications(science);
		
		// This tutor is new and has no students
		// But he will be able to teach science and mathematics
		Tutor t2 = new Tutor("DEF456", "Alan Bridges", 15000);
		t2.addSubjectToQualifications(mathematics);
		t2.addSubjectToQualifications(science);
		
		// This tutor is the only tutor who can teach History
		Tutor t3 = new Tutor("GHI678", "Linda Histroia", 20000);
		t3.addSubjectToQualifications(history);
//		t3.addSubjectToQualifications(science);
		
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);

		// this only works because we are cascading from tutor to student
		t1.createStudentAndAddToSupervisionGroup("Marco Fortes", "1-FOR-2010", "1 The Street", "Georgia", "484848");
		t1.createStudentAndAddToSupervisionGroup("Kath Grainer", "2-GRA-2009", "2 Kaths Street", "Georgia", "939393");
		t3.createStudentAndAddToSupervisionGroup("Sandra Perkins", "3-PER-2009", "4 The Avenue", "Georgia", "939393");
		
		tx.commit();
		em.close();
	}

	
}
