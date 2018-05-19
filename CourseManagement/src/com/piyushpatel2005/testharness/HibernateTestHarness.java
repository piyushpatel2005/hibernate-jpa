package com.piyushpatel2005.testharness;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
		TypedQuery<Student> q = em.createQuery("from Student as student where student.address.city = :name", Student.class);
		
		q.setParameter("name", "Georgia");
		
		List<Student> allStudents = q.getResultList();
		for(Student student: allStudents)  {
			System.out.println(student);
		}
		
		TypedQuery<Tutor> tutorQuery = em.createQuery("from Tutor as tutor where tutor.supervisionGroup is empty", Tutor.class);
		List<Tutor> tutors = tutorQuery.getResultList();
		for(Tutor t: tutors) {
			System.out.println(t);
		}
		
		tutorQuery = em.createQuery("from Tutor as tutor where tutor.supervisionGroup is not empty", Tutor.class);
		tutors = tutorQuery.getResultList();
		for(Tutor t: tutors) {
			System.out.println(t);
		}
		
		Subject science = em.find(Subject.class, 2);
		// list of all students whose supervisor can teach science
		Query scienceQuery = em.createQuery("from Student as student where :subject member of student.supervisor.subjectsQualifiedToTeach");
		scienceQuery.setParameter("subject", science);
		List<Student> scienceTutors = scienceQuery.getResultList();
		for(Student t: scienceTutors) {
			System.out.println(t);
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
		Tutor t1 = new Tutor("ABC123", "David Banks", 2939393);
		t1.addSubjectToQualifications(mathematics);
		t1.addSubjectToQualifications(science);
		
		// This tutor is new and has no students
		// But he will be able to teach science and mathematics
		Tutor t2 = new Tutor("DEF456", "Alan Bridges", 0);
		t2.addSubjectToQualifications(mathematics);
		t2.addSubjectToQualifications(science);
		
		// This tutor is the only tutor who can teach History
		Tutor t3 = new Tutor("GHI678", "Linda Histroia", 0);
		t3.addSubjectToQualifications(history);
		
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);

		// this only works because we are cascading from tutor to student
		t1.createStudentAndAddToSupervisionGroup("Marco Fortes", "1-FOR-2010", "1 The Street", "Anytown", "484848");
		t1.createStudentAndAddToSupervisionGroup("Kath Grainer", "2-GRA-2009", "2 Kaths Street", "Georgia", "939393");
		t3.createStudentAndAddToSupervisionGroup("Sandra Perkins", "3-PER-2009", "4 The Avenue", "Georgia", "939393");
		
		tx.commit();
		em.close();
	}

	
}
