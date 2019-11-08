package com.megaproject.service;

import com.megaproject.dto.UserRequest;
import com.megaproject.entity.User;
import com.megaproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUser(UserRequest userRequest) {
        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setUserName(userRequest.getUserName());
        user.setAge(userRequest.getAge());
        user.setUserEmailId(userRequest.getUserEmailId());
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent()?user.get():null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UserRequest userRequest) {
        User userUpdate = userRepository.getOne(userRequest.getUserId());
        userUpdate.setUserName(userRequest.getUserName());
        userUpdate.setAge(userRequest.getAge());
        userUpdate.setUserEmailId(userRequest.getUserEmailId());
        return userRepository.save(userUpdate);
    }
}
