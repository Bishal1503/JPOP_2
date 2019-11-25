package com.JPoP2.service.impl;

import com.JPoP2.config.SpringSecurityConfig;
import com.JPoP2.entity.Library;
import com.JPoP2.model.Book;
import com.JPoP2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("v1")
public class LibraryServiceImplRestTemplate implements com.JPoP2.service.LibraryService {
	@Autowired
	private com.JPoP2.LibraryService.repository.LibraryRepository libraryRepository;

	@Autowired
	private SpringSecurityConfig securityConfig;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${userservice.url}")
	private String userServiceUrl;

	@Value("${bookservice.url}")
	private String bookServiceUrl;

	@Override
	public Map<String, String> validateLogin(String emailId, String password) {
		Map<String, String> authToken = new HashMap<>();
		authToken.put("authToken", securityConfig.validateLogin(emailId, password));
		return authToken;
	}

	@Override
	public List<Book> getAllBooks() {
		return Arrays.asList(restTemplate.exchange(bookServiceUrl, HttpMethod.GET, null, Book[].class).getBody());
	}

	@Override
	public Book getBookById(Long bookId) {
		return restTemplate.exchange(bookServiceUrl + "/" + bookId, HttpMethod.GET, null, Book.class).getBody();
	}

	@Override
	public Book addBook(Book book) {
		return restTemplate.exchange(bookServiceUrl, HttpMethod.POST, new HttpEntity<>(book), Book.class).getBody();
	}

	@Override
	public Long deleteBookById(Long bookId) {
		return restTemplate.exchange(bookServiceUrl + "/" + bookId, HttpMethod.DELETE, null, Long.class).getBody();
	}

	@Override
	public List<User> getAllUsers() {
		return Arrays.asList(restTemplate.exchange(userServiceUrl, HttpMethod.GET, null, User[].class).getBody());
	}

	@Override
	public User getUserById(Long userId) {
		return restTemplate.exchange(userServiceUrl + "/" + userId, HttpMethod.GET, null, User.class).getBody();
	}

	@Override
	public User addUser(User user) {
		securityConfig.addUserForAuthentication(user.getEmailId(), user.getRole());
		return restTemplate.exchange(userServiceUrl, HttpMethod.POST, new HttpEntity<>(user), User.class).getBody();
	}

	@Override
	public Long deleteUserById(Long userId) {
		securityConfig.deleteUserForAuthentication(this.getUserById(userId).getEmailId());
		return restTemplate.exchange(userServiceUrl + "/" + userId, HttpMethod.DELETE, null, Long.class).getBody();
	}

	@Override
	public User updateUserById(Long userId, User user) {
		return restTemplate.exchange(userServiceUrl + "/" + userId, HttpMethod.PUT, new HttpEntity<>(user), User.class)
				.getBody();
	}

	@Override
	public Library issueBookToUser(Long userId, Long bookId) {
		Library library = new Library();

		library.setUserId(userId);
		library.setBookId(bookId);

		return libraryRepository.save(library);
	}
}
