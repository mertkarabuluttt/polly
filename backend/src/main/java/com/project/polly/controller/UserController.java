package com.project.polly.controller;

import com.project.polly.entity.Users;
import com.project.polly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path="/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email) {
        Users n = new Users();
        n.setFirstName(firstName);
        n.setLastName(lastName);
        n.setEmail(email);
        userRepository.save(n);
        return "User saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        //This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/id")
    public @ResponseBody
    Optional<Users> getById(@RequestParam Integer id) {
        return userRepository.findById(id);
    }

    @GetMapping(path = "/firstname")
    public @ResponseBody
    List<Users> getByFirstName(@RequestParam String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    @GetMapping(path = "/lastname")
    public @ResponseBody
    List<Users> getByLastName(@RequestParam String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @GetMapping(path = "/email")
    public @ResponseBody
    Users getByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }
}