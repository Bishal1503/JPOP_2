package com.JPoP2.service.impl;


import com.JPoP2.client.BookClient;
import com.JPoP2.client.UserClient;
import com.JPoP2.config.SpringSecurityConfig;
import com.JPoP2.entity.Library;
import com.JPoP2.model.Book;
import com.JPoP2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.JPoP2.LibraryService.repository.LibraryRepository;
import com.epam.libraryservice.service.LibraryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("v2")
public class LibraryServiceImplFeignClient implements LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private SpringSecurityConfig securityConfig;

    @Autowired
    UserClient userClient;

    @Autowired
    BookClient bookClient;

    @Override
    public Map<String, String> validateLogin(String emailId, String password) {
        Map<String, String> authToken = new HashMap<>();
        authToken.put("authToken", securityConfig.validateLogin(emailId, password));
        return authToken;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookClient.getAllBooks().getBody();
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookClient.getBookById(bookId).getBody();
    }

    @Override
    public Book addBook(Book book) {
        return bookClient.addBook(book).getBody();
    }

    @Override
    public Long deleteBookById(Long bookId) {
        return bookClient.deleteBookById(bookId).getBody();
    }

    @Override
    public List<User> getAllUsers() {
        return userClient.getAllUsers().getBody();
    }

    @Override
    public User getUserById(Long userId) {
        return userClient.getUserById(userId).getBody();
    }

    @Override
    public User addUser(User user) {
        securityConfig.addUserForAuthentication(user.getEmailId(), user.getRole());
        return userClient.addUser(user).getBody();
    }

    @Override
    public Long deleteUserById(Long userId) {
        securityConfig.deleteUserForAuthentication(this.getUserById(userId).getEmailId());
        return userClient.deleteUserById(userId).getBody();
    }

    @Override
    public User updateUserById(Long userId, User user) {
        return userClient.updateUserById(userId, user).getBody();
    }

    @Override
    public Library issueBookToUser(Long userId, Long bookId) {
        Library library = new Library();

        library.setUserId(userId);
        library.setBookId(bookId);

        return libraryRepository.save(library);
    }
}
