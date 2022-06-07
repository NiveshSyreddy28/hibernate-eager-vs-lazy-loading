package com.zemoso.hibernate.demo;

import com.zemoso.hibernate.entity.Course;
import com.zemoso.hibernate.entity.Instructor;
import com.zemoso.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateCoursesDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession(); {

        }
        try {


            //start a transaction
            session.beginTransaction();

            // get the instructor from db
            int id = 1;
            Instructor instructor1 = session.get(Instructor.class,id);

            //create some courses
            Course course1 = new Course("Air Guitar - The ultimate Guide");
            Course course2 = new Course("The pinball Masterclass");

            //add courses to instructor
            instructor1.add(course1);
            instructor1.add(course2);

            //save the courses
            session.save(course1);
            session.save(course2);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
