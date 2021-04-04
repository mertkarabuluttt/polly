package com.project.polly.repository;

import com.project.polly.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

}
