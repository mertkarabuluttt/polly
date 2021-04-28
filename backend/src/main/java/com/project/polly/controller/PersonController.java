package com.project.polly.controller;

import com.project.polly.entity.Person;
import com.project.polly.exceptions.PersonNotFoundException;
import com.project.polly.repository.PersonRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {


    private final PersonRepository repository;

    PersonController (PersonRepository repository) {
        this.repository = repository;
    }

    //@GetMapping("/person")
    //Iterable<Person> all() {
    //    return repository.findAll();
    //}

    @GetMapping("/person")
    CollectionModel<EntityModel<Person>> all() {

        List<EntityModel<Person>> people = repository.findAll().stream()
                .map(person -> EntityModel.of(person,
                        linkTo(methodOn(PersonController.class).getById(person.getId())).withSelfRel(),
                        linkTo(methodOn(PersonController.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(people, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @PostMapping(path = "/person")
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("/person/{id}")
    EntityModel<Person> getById(@PathVariable Integer id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return EntityModel.of(person, //
                linkTo(methodOn(PersonController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(PersonController.class).all()).withRel("person"));
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