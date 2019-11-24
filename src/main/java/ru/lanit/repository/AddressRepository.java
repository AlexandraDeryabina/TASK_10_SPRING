package ru.lanit.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lanit.model.Address;

@Repository
public class AddressRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public AddressRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Address address) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(address);
            session.getTransaction().commit();
        }
    }
}
