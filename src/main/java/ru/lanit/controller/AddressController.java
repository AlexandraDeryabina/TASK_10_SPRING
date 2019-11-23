package ru.lanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.service.AddressService;
import ru.lanit.service.PersonService;

import java.util.Map;

@Controller
public class AddressController {
    private PersonService personService;
    private AddressService addressService;

    @Autowired
    public AddressController(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public ModelAndView saveAddress(
            @RequestParam Map<String, String> request
    ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            addressService.save(request);

            modelAndView.addObject("personList", personService.getList(true));
            modelAndView.setViewName("/result");
            return modelAndView;
        } catch (Exception error) {
            modelAndView.addObject("error", error.getMessage());
            modelAndView.setViewName("/error");
            return modelAndView;
        }

    }
}
