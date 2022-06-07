package com.zemoso.hibernate.demo;

//import com.zemoso.hibernate.entity.Student;
import com.zemoso.hibernate.entity.Instructor;
import com.zemoso.hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteDemo {
    public static void main(String[] args) {

        //create session factory
        SessionFactory factory= new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession(); {

        }
        try {
           //start a transaction
            session.beginTransaction();

            // get instructor by primary key / id
            int id = 1;
            Instructor tempInstructor = session.get(Instructor.class,id);
            System.out.println("Found Instructor: " + tempInstructor);

            //delete the instructor
            if (tempInstructor != null){
                System.out.println("Deleting: " + tempInstructor);
                session.delete(tempInstructor);
            }

            //commit transaction
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
