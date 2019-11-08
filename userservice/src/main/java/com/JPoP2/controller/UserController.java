package com.megaproject.controller;

import com.megaproject.dto.UserRequest;
import com.megaproject.entity.User;
import com.megaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/{user_id}")
    public User getUser(@PathVariable(value = "user_id") Long id){
        return userService.getUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

    @PutMapping
    public User updateUser(@RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest);
    }

    @DeleteMapping(value = "/{user_id}")
    public void deleteUser(@PathVariable(value = "user_id") Long id){
        userService.deleteUser(id);
    }
}
