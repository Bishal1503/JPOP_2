package com.JPoP2.controller;

import com.JPoP2.entity.User;
import com.JPoP2.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/user-service/")
@Api(value = "User Service")
public class UserController {
    private static final Logger log = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "View a list of all the available users", response = List.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully retrieved all the users"))
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        log.log(Level.INFO, "Calling getAllUsers() method");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }


    @ApiOperation(value = "View an user by id", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieving user by id"),
            @ApiResponse(code = 404, message = "User with specified id not found")})
    @GetMapping("/user/{user_id}")
    public ResponseEntity<User> getUserById(
            @ApiParam(value = "Id of user to be retireved from database", required = true) @PathVariable Long id) {
        log.log(Level.INFO, "Calling getUserById()");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }


    @ApiOperation(value = "Add an user", response = User.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = "Returns the newly added user"))
    @PostMapping("/user")
    public ResponseEntity<User> addUser(
            @ApiParam(value = "User object to be stored in database", required = true) @RequestBody User user) {
        log.log(Level.INFO, "Calling addUser()");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }


    @ApiOperation(value = "Update an user", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns the updated user"),
            @ApiResponse(code = 404, message = "User wih specified id not found")})
    @PutMapping("/user/{user_id}")
    public ResponseEntity<User> updateUserById(
            @ApiParam(value = "Id of user and the user object to be updated in database", required = true) @PathVariable Long id,
            @ApiParam(value = "User object to be updated in database", required = true) @RequestBody User user) {
        log.log(Level.INFO, "Calling updateUserById()");
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(id, user));
    }

    @ApiOperation(value = "Delete an user", response = Long.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted user by id"),
            @ApiResponse(code = 404, message = "User wih specified id not found")})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUserById(
            @ApiParam(value = "Id of user to be deleted from database", required = true) @PathVariable Long id) {
        log.log(Level.INFO, "Calling deleteUserById()");
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUserById(id));
    }
}
