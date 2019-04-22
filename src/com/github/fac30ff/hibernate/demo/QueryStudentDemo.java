package com.github.fac30ff.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.fac30ff.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//begin  a transaction
			session.beginTransaction();
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			// display the students
			theStudents.forEach(System.out::println);
			//query students: last name = doe
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			// display the students
			System.out.println("\nStudents who have last name of Doe: ");
			displayStudents(theStudents);
			//query students: last name = doe or first name = daffy
			theStudents = session.createQuery("from Student s where s.lastNmae='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("\nStudents who have last name of Doe or first name Daffy: ");
			displayStudents(theStudents);
			//query students: where email like @mail.com
			theStudents = session.createQuery("from Student s where s.email Like '%mail.com'").getResultList();
			System.out.println("\nStudents whose email ends with @mail.com: ");
			displayStudents(theStudents);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
	
	private static void displayStudents(List<Student> list) {
		for (Student student : list) {
			System.out.println(student);
		}
	}

}
