package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public void save(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("")
    public User get(@RequestParam("id") Long id){
        return userService.query(id);
    }

    @PutMapping("")
    public void update(@RequestBody User user){
        userService.update(user);
    }


}
