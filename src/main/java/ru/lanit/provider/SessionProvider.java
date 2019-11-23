package ru.lanit.provider;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SessionProvider {
    private SessionFactory sessionFactory;
    private static Logger log = Logger.getLogger(SessionProvider.class.getName());

    private SessionProvider() {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (HibernateException e) {
            log.warning(e.getMessage());
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
