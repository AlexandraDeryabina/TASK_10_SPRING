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

    public void save(Map<String, String> request) {
        Person person = new Person(
                request.get("name") != "" ? request.get("name") : null,
                request.get("surname") != "" ? request.get("surname") : null,
                LocalDate.parse(request.get("dateOfBirth")));
        person.setPatronymic(request.get("patronymic"));
        repository.save(person);
    }
}
