package com.JPoP2.client;

import com.JPoP2.fallback.BookClientFallback;
import com.JPoP2.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "${bookservice.name}", fallback = BookClientFallback.class)
public interface BookClient {
	@PostMapping("/api/book")
	public ResponseEntity<Book> addBook(@RequestBody Book book);

	@GetMapping("/api/book")
	public ResponseEntity<List<Book>> getAllBooks();

	@GetMapping("/api/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id);

	@DeleteMapping("/api/book/{id}")
	public ResponseEntity<Long> deleteBookById(@PathVariable Long id);

	@PutMapping("/api/book/{id}")
	public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book);
}