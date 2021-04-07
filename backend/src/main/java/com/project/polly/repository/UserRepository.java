package com.project.polly.repository;

import com.project.polly.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

    List<Users> findByFirstName(@Param("firstName") String firstName);
    List<Users> findByLastName(@Param("lastName") String lastName);
    Users findByEmail(@Param("email") String email);

}
