package com.JPoP2.fallback;

import com.JPoP2.client.BookClient;
import com.JPoP2.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookClientFallback implements BookClient {

    @Override
    public ResponseEntity<Book> addBook(Book book) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ArrayList<Book>());
    }

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }

    @Override
    public ResponseEntity<Long> deleteBookById(Long id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }

    @Override
    public ResponseEntity<Book> updateBookById(Long id, Book book) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}