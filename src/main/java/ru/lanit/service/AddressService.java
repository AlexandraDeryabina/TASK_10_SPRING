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

    public void save(Map<String, String> request) {
        Address address = new Address();
        address.setStreet(request.get("street") != "" ? request.get("street") : null);
        address.setHouse(request.get("house") != "" ? request.get("house") : null);
        address.setFlat(request.get("flat"));

        address.setPerson(personService.
                getById(Integer.parseInt(request.get("person"))));

        addressRepository.save(address);
    }
}
