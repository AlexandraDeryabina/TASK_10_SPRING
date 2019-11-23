package ru.lanit.repository;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lanit.model.Address;
import ru.lanit.provider.SessionProvider;

@Repository
public class AddressRepository {
    private SessionProvider provider;

    @Autowired
    public AddressRepository(SessionProvider provider) {
        this.provider = provider;
    }

    public void save(Address address) {
        try (Session session = provider.getSession()) {
            session.beginTransaction();
            session.save(address);
            session.getTransaction().commit();
        }
    }
}
