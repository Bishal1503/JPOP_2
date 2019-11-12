package com.JPoP2.client;

import com.JPoP2.fallback.UserClientFallback;
import com.JPoP2.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "${userservice.name}", fallback = UserClientFallback.class)
public interface UserClient {
	@PostMapping("/api/user")
	public ResponseEntity<User> addUser(@RequestBody User user);

	@GetMapping("/api/user")
	public ResponseEntity<List<User>> getAllUsers();

	@GetMapping("/api/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id);

	@DeleteMapping("/api/user/{id}")
	public ResponseEntity<Long> deleteUserById(@PathVariable Long id);

	@PutMapping("/api/user/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user);
}
