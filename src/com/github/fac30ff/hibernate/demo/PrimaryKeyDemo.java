package com.github.fac30ff.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.fac30ff.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create session
		Session session = factory.getCurrentSession();
				
		try {
			//use session object to save Java object
			//create a student object
			System.out.println("Creating new student object...");
			Student tempStudent1 = new Student("Duffy", "Duck", "duffy@mail.com");
			Student tempStudent2 = new Student("Small", "Talk", "small@mail.com");
			Student tempStudent3 = new Student("Villie", "Orca", "villie@mail.com");
			//begin  a transaction
			session.beginTransaction();
			// save the student object
			System.out.println("Saving the students...");
			session.saveOrUpdate(tempStudent1);
			session.saveOrUpdate(tempStudent2);
			session.saveOrUpdate(tempStudent3);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
