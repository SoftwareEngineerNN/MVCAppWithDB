package com.muradov.springcourse.controllers;

import com.muradov.springcourse.dao.PersonDAO;
import com.muradov.springcourse.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        // return all people from DAO and transfer them to views
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // get 1 person by id from DAO and send to views
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people/successSub";
    }

    @GetMapping("/successSub")
    public String successSubmit() {
        return "people/successSub";
    }
    @GetMapping("/welcome")
    public String welcomePage() {
        return "people/welcome";
    }
}
