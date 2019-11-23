package ru.lanit.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lanit.model.Person;
import ru.lanit.provider.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PersonRepository {
    private SessionProvider provider;

    @Autowired
    public PersonRepository(SessionProvider provider) {
        this.provider = provider;
    }

    public List<Person> getList(boolean needFullFetch) {
        try (Session session = provider.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> query = builder.createQuery(Person.class);
            Root<Person> root = query.from(Person.class);
            if (needFullFetch) {
                root.fetch("addressList", JoinType.LEFT);
            }
            query.distinct(true);
            query.orderBy(builder.asc(root.get("fullName")));
            Query<Person> q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public Person getById(int id) {
        try (Session session = provider.getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> query = builder.createQuery(Person.class);
            Root<Person> root = query.from(Person.class);
            query.select(root).where(builder.equal(root.get("id"), id));
            Query<Person> q = session.createQuery(query);
            return q.getSingleResult();
        }
    }

    public void save(Person person) {
        try (Session session = provider.getSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        }
    }
}
