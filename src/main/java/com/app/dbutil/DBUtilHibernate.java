package com.app.dbutil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBUtilHibernate {
	private  static SessionFactory sessionFactory = null;	
	private DBUtilHibernate(){}
	public static SessionFactory getSessionFactroy() {
		try {
			if(sessionFactory == null){
				Configuration configure = new Configuration().configure();
				StandardServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configure.getProperties())
						.build();
				sessionFactory = configure.buildSessionFactory(sr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionFactory;
	}

}
