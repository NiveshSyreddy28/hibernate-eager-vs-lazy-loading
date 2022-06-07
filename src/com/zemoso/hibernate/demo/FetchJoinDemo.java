package com.zemoso.hibernate.demo;

import com.zemoso.hibernate.entity.Course;
import com.zemoso.hibernate.entity.Instructor;
import com.zemoso.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;


public class FetchJoinDemo {
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

            //get course for the instructor
            //Using HQL
            int id = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i "
                            + "JOIN FETCH i.courses "
                            + "where i.id=:theInstructorId",
                            Instructor.class);

            //set parameter on query
            query.setParameter("theInstructorId",id);

            //execute the query and get the instructor
            Instructor instructor1 = query.getSingleResult();

            System.out.println("Instructor: "+instructor1);

            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

            //close the session
            session.close();

            //get courses for the instructor
            System.out.println("Courses: " + instructor1.getCourses());
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
