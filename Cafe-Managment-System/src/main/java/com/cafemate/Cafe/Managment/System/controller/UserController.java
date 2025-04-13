package com.cafemate.Cafe.Managment.System.controller;

import com.cafemate.Cafe.Managment.System.model.User;
import com.cafemate.Cafe.Managment.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public  String createUser(@RequestBody User user){
        return userService.create(user);
    }
    @GetMapping("/get/{id}")
    public  User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }
}
