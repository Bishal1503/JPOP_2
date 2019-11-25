package com.JPoP2.service;

import com.JPoP2.entity.Library;
import com.JPoP2.model.Book;
import com.JPoP2.model.User;

import java.util.List;
import java.util.Map;


public interface LibraryService {
    public Map<String, String> validateLogin(String emailId, String password);

    public List<Book> getAllBooks();

    public Book getBookById(Long bookId);

    public Book addBook(Book book);

    public Long deleteBookById(Long bookId);

    public List<User> getAllUsers();

    public User getUserById(Long userId);

    public User addUser(User user);

    public Long deleteUserById(Long userId);

    public User updateUserById(Long userId, User user);

    public Library issueBookToUser(Long userId, Long bookId);
}
