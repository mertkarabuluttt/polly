package com.project.polly.controller;

import com.project.polly.entity.Users;
import com.project.polly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return "User Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        //This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}