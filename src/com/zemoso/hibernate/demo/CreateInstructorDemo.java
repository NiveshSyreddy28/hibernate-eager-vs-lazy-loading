package com.zemoso.hibernate.demo;

//import com.zemoso.hibernate.entity.Student;
import com.zemoso.hibernate.entity.Course;
import com.zemoso.hibernate.entity.Instructor;
import com.zemoso.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class CreateInstructorDemo {
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
            //create the objects
            Instructor instructor2 = new Instructor("Shyam","Radha","shyamradha@gmail.com");

            InstructorDetail instructorDetail2 = new InstructorDetail(
                    "radhaShyam","Singing");

            //associate the objects
            instructor2.setInstructorDetail(instructorDetail2);

            //start a transaction
            session.beginTransaction();

            // save the objects
            System.out.println("Saving the instructor: " + instructor2);
            session.save(instructor2);

            //commit transaction
            session.getTransaction().commit();
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
