package com.johnscodinglab.person.controller;

import com.johnscodinglab.person.dto.NewPersonDto;
import com.johnscodinglab.person.model.Person;
import com.johnscodinglab.person.service.PersonService;
import com.johnscodinglab.person.dto.PersonUpdateDto;
import com.johnscodinglab.person.enums.SortingOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public List<Person> getPeople(@RequestParam(value = "sort", required = false, defaultValue = "ASC") SortingOrder sort) {
        return personService.getPeople(sort);
    }

    @GetMapping("{id}")
    public Person getPersonById(@Valid @Positive @PathVariable("id") Integer id) {
        return personService.getPersonById(id);
    }

    @DeleteMapping("{id}")
    public void deletePersonById(@Valid @Positive @PathVariable("id") Integer id) {
        personService.deletePersonById(id);
    }

    @PostMapping("")
    public void addPerson(@Valid @RequestBody NewPersonDto person) {
        personService.addPerson(person);
    }


    @PutMapping("{id}")
    public void updatePerson(@PathVariable("id") Integer id, @RequestBody PersonUpdateDto request) {
        personService.updatePerson(id, request);
    }
}
