package com.project.polly.controller;

import com.project.polly.assembler.PersonModelAssembler;
import com.project.polly.entity.Person;
import com.project.polly.exceptions.PersonNotFoundException;
import com.project.polly.repository.PersonRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PersonController {


    private final PersonRepository repository;
    private final PersonModelAssembler assembler;

    PersonController (PersonRepository repository, PersonModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/person")
    public CollectionModel<EntityModel<Person>> all() {

        List<EntityModel<Person>> people = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(people, linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }

    @PostMapping(path = "/person")
    ResponseEntity<?> newPerson(@RequestBody Person newPerson) {
        EntityModel<Person> entityModel = assembler.toModel(repository.save(newPerson));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/person/{id}")
    public EntityModel<Person> getById(@PathVariable Integer id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return assembler.toModel(person);
    }

    @PutMapping(path = "/person/{id}")
    public ResponseEntity<?> replacePerson(@RequestBody Person newPerson, @PathVariable Integer id) {
        Person updatedPerson = repository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setEmail(newPerson.getEmail());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });

        EntityModel<Person> entityModel = assembler.toModel(updatedPerson);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(path = "/person/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
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