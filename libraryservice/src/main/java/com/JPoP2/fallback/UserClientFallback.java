package com.JPoP2.fallback;

import com.epam.libraryservice.client.UserClient;
import com.epam.libraryservice.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserClientFallback implements UserClient {

	@Override
	public ResponseEntity<User> addUser(User user) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
	}

	@Override
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ArrayList<User>());
	}

	@Override
	public ResponseEntity<User> getUserById(Long id) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
	}

	@Override
	public ResponseEntity<Long> deleteUserById(Long id) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
	}

	@Override
	public ResponseEntity<User> updateUserById(Long id, User user) {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
	}
}