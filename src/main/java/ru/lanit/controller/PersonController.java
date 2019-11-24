package ru.lanit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lanit.service.PersonService;

import java.util.Map;

@Controller
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/fullName", method = RequestMethod.POST)
    public ModelAndView savePerson(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("dateOfBirth") String dateOfBirth
    ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            personService.save(name, surname, patronymic, dateOfBirth);

            modelAndView.addObject("personList", personService.getList());
            modelAndView.setViewName("/address");
            return modelAndView;
        } catch (Exception error) {
            modelAndView.addObject("error", error.getMessage());
            modelAndView.setViewName("/error");
            return modelAndView;
        }
    }
}
