package com.java.expenseTrackerApi.controller;

import com.java.expenseTrackerApi.entity.User;
import com.java.expenseTrackerApi.entity.UserModel;
import com.java.expenseTrackerApi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserModel userModel)
    {
        User user = userService.createUser(userModel);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> readUser(@PathVariable("id") Long Id)
    {
        User user = userService.readUser(Id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserModel userModel, @PathVariable("id") Long Id)
    {
        User updateUser = userService.updateUser(userModel, Id);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam("id") Long Id)
    {
        userService.deleteUser(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
