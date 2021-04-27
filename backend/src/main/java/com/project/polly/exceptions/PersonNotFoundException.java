package com.project.polly.exceptions;

public class PersonNotFoundException extends RuntimeException{

    public PersonNotFoundException(Integer id) {
        super("Could not find person with id: " + id);
    }
}
