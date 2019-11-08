package com.JPoP2.service.impl;

import com.JPoP2.entity.Book;
import com.JPoP2.error.BookNotFoundException;
import com.JPoP2.repository.BookRepository;
import com.JPoP2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        book.setId(null);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id: " + id + " Not Found"));
    }

    @Override
    public Long deleteBookById(Long id) {
        if (!bookRepository.existsById(id))
            throw new BookNotFoundException("Could not delete Book with id: " + id);

        bookRepository.deleteById(id);
        return id;
    }

    @Override
    public Book updateBookById(Long id, Book book) {
        if (!bookRepository.existsById(id))
            throw new BookNotFoundException("Could not update Book with id: " + id);

        book.setId(id);
        return bookRepository.save(book);
    }
}
