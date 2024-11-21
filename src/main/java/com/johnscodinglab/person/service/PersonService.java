package com.johnscodinglab.person.service;

import com.johnscodinglab.person.dto.NewPersonDto;
import com.johnscodinglab.person.dto.PersonUpdateDto;
import com.johnscodinglab.person.enums.SortingOrder;
import com.johnscodinglab.exception.DuplicateResourceException;
import com.johnscodinglab.exception.ResourceNotFoundException;
import com.johnscodinglab.person.model.Person;
import com.johnscodinglab.person.repository.PersonRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public List<Person> getPeople(SortingOrder sort) {
        return personRepository.findAll(Sort.by(Sort.Direction.valueOf(sort.name()), "id"));
    }

//    Get person by id
    public Person getPersonById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person with id " + id + " not found"));
    }

//    Delete person by id
    public void deletePersonById(Integer id) {
        boolean existsPersonByID = personRepository.existsById(id);
        if (!existsPersonByID) {
            throw new ResourceNotFoundException("Person with id " + id + " not found");
        }
        personRepository.deleteById(id);
    }

//    Add new person
    public void addPerson(NewPersonDto personRequest) {
        if (personRequest.email() != null && !personRequest.email().isEmpty()) {
            boolean existsPersonEmail = personRepository.existsByEmail(personRequest.email());
            if (existsPersonEmail) {
                throw new DuplicateResourceException("Email is taken");
            }
        }

        Person person = new Person(
                personRequest.name(),
                personRequest.age(),
                personRequest.gender(),
                personRequest.email());

        personRepository.save(person);
    }

//    update person
    public void updatePerson(Integer id, PersonUpdateDto personRequest) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person with id " + id + " not found"));

        if (personRequest.name() != null &&
                !personRequest.name().isEmpty()
                && !personRequest.name().equals(person.getName())) {
            person.setName(personRequest.name());
            personRepository.save(person);
        }

        if (personRequest.age() != null &&
                !personRequest.age().equals(person.getAge())) {
            person.setAge(personRequest.age());
            personRepository.save(person);
        }
    }
}
