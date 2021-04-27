package com.project.polly.controller;

import com.project.polly.entity.Person;
import com.project.polly.exceptions.PersonNotFoundException;
import com.project.polly.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {


    private final PersonRepository repository;

    PersonController (PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/person")
    Iterable<Person> all() {
        return repository.findAll();
    }

    @PostMapping(path = "/person")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("/person/{id}")
    Person getById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/person/{id}")
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setEmail(newPerson.getEmail());
                    return repository.save(person);
                }).orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/person/{id}")
    void deletePerson(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @GetMapping(path = "/person/firstname")
    public @ResponseBody
    List<Person> getByFirstName(@RequestParam String firstName) {
        return repository.findByFirstName(firstName);
    }

    @GetMapping(path = "/person/lastname")
    public @ResponseBody
    List<Person> getByLastName(@RequestParam String lastName) {
        return repository.findByLastName(lastName);
    }

    @GetMapping(path = "/person/email")
    public @ResponseBody
    Person getByEmail(@RequestParam String email) {
        return repository.findByEmail(email);
    }
}