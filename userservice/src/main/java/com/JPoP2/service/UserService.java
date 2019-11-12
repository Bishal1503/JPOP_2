package com.JPoP2.service;

import com.JPoP2.entity.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public Long deleteUserById(Long id);

    public User updateUserById(Long id, User user);
}
