package com.JPoP2.service.impl;

import com.JPoP2.entity.User;
import com.JPoP2.error.UserNotFoundException;
import com.JPoP2.repository.UserRepository;
import com.JPoP2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

	@Override
	public User addUser(User user) {
		user.setId(null);
		return userRepository.save(user);
	}

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + id + " Not Found"));
    }

    @Override
    public Long deleteUserById(Long id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("Could not delete User with id: " + id);

        userRepository.deleteById(id);
        return id;
    }

    @Override
    public User updateUserById(Long id, User user) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException("Could not update User with id: " + id);

        user.setId(id);
        return userRepository.save(user);
    }
}
