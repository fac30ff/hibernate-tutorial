package com.github.fac30ff.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.fac30ff.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//student id
			int studentId = 1111111111;
			//begin  a transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			//retrieve student based on the id: primary key
			System.out.println("\nGetting stuent with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			System.out.println("Updating student... ");
			session.delete(myStudent);
			//commit the transaction
			session.getTransaction().commit();
			//delete on the fly
			session = factory.getCurrentSession();
			session.beginTransaction();
			//query for deleting student with id
			session.createQuery("delete from Student where id=1111111111").executeUpdate();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
