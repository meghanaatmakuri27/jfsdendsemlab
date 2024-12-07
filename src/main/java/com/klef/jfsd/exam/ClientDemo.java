package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {

        insertCustomer();
        performCriteriaQueries();
    }

    private static void insertCustomer() {
    	  Configuration config = new Configuration();
          config.configure("hibernate.cfg.xml");
          SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer c1 = new Customer();
        c1.setName("meghana");
        c1.setEmail("meghanaatmakuri7@gmail.com");
        c1.setAge(19);
        c1.setLocation("Vijayawada");

        Customer c2 = new Customer();
        c2.setName("bhuvana atmakuri");
        c2.setEmail("bhuvana3914@gmail.com");
        c2.setAge(20);
        c2.setLocation("vijayawada");

        Customer c3 = new Customer();
        c3.setName("hema");
        c3.setEmail("hemaatmakuri@gmail.com");
        c3.setAge(40);
        c3.setLocation("Vijayawada");

        session.save(c1);
        session.save(c2);
        session.save(c3);

        transaction.commit();
        session.close();
        System.out.println("Records inserted successfully.");
    }

    private static void performCriteriaQueries() {
    	Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Customer.class);

        criteria.add(Restrictions.lt("age",20 ));
        List<Customer> results = criteria.list();
        System.out.println("Customers with age < 20:");
        results.forEach(c -> System.out.println(c.getName()));

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.eq("location", "Vijayawada"));
        results = criteria.list();
        System.out.println("Customers with vijayawada location:");
        results.forEach(c -> System.out.println(c.getName()));

        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("name", "%m%"));
        results = criteria.list();
        System.out.println("Customers with names containing 'm':");
        results.forEach(c -> System.out.println(c.getName()));

        session.close();
    }
}
