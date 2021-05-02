package com.project.polly.assembler;

import com.project.polly.controller.PersonController;
import com.project.polly.entity.Person;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {


    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person, //
                linkTo(methodOn(PersonController.class).getById(person.getId())).withSelfRel(),
                linkTo(methodOn(PersonController.class).all()).withRel("person"));
    }

}
