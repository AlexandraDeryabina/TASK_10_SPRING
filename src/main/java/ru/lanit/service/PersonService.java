package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.model.Person;
import ru.lanit.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {
    private PersonRepository repository;

    @Autowired
    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getList(boolean needFullFetch) {
        return repository.getList(needFullFetch);
    }

    public List<Person> getList() {
        return getList(false);
    }

    public Person getById(int id) {
        return repository.getById(id);
    }

    public void save(String name, String surname, String patronymic, String dateOfBirth) {
        Person person = new Person(
                name != "" ? name : null,
                surname != "" ? surname : null,
                LocalDate.parse(dateOfBirth));
        person.setPatronymic(patronymic);
        repository.save(person);
    }
}
