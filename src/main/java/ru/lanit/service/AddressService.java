package ru.lanit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lanit.model.Address;
import ru.lanit.repository.AddressRepository;

import java.util.Map;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private PersonService personService;

    @Autowired
    public AddressService(AddressRepository addressRepository, PersonService personService) {
        this.addressRepository = addressRepository;
        this.personService = personService;
    }

    public void save(String street, String house, String flat, String person) {
        Address address = new Address();
        address.setStreet(street != "" ? street : null);
        address.setHouse(house != "" ? house : null);
        address.setFlat(flat);

        address.setPerson(personService.
                getById(Integer.parseInt(person)));

        addressRepository.save(address);
    }
}
