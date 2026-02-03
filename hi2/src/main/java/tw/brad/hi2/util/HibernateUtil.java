package tw.brad.hi2.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tw.brad.hi2.entity.Customer;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration config = new Configuration();
			config.configure("hi.cfg.xml");
			
			config.addAnnotatedClass(Customer.class);
			
			sessionFactory = config.buildSessionFactory();
		}
		
		return sessionFactory;
	}
}
