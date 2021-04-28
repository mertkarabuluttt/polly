package com.project.polly.repository;

import com.project.polly.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByFirstName(@Param("firstName") String firstName);
    List<Person> findByLastName(@Param("lastName") String lastName);
    Person findByEmail(@Param("email") String email);

}
