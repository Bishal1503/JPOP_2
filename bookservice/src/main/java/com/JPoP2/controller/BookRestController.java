package com.JPoP2.controller;


import com.JPoP2.entity.Book;
import com.JPoP2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/")
@Api(value = "Book Service")
public class BookRestController {
    private static final Logger log = Logger.getLogger(BookRestController.class.getName());

    @Autowired
    private BookService bookService;


    /**
     * View a list of all available books
     *
     * @return
     */
    @ApiOperation(value = "View a list of all available books", response = List.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully retrieved all the books"))
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.log(Level.INFO, "Calling getAllBooks()");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }


    /**
     * Add a book
     *
     * @param book
     * @return
     */
    @ApiOperation(value = "Add a book", response = Book.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = "Returns newly added book"))
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(
            @ApiParam(value = "Book object to be stored in database", required = true) @RequestBody Book book) {
        log.log(Level.INFO, "Calling addBook()");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(book));
    }

    /**
     * Retrieve
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "View a book by id", response = Book.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved book by id"),
            @ApiResponse(code = 404, message = "Book with specified id not found")})
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(
            @ApiParam(value = "Id of book to be retireved from database", required = true) @PathVariable Long id) {
        log.log(Level.INFO, "Calling getBookById()");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id));
    }

    /**
     * Update a book
     *
     * @param id
     * @param book
     * @return
     */
    @ApiOperation(value = "Update a book", response = Book.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns the updated book"),
            @ApiResponse(code = 404, message = "Book wih specified id not found")})
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(
            @ApiParam(value = "Id of book to be updated in database", required = true) @PathVariable Long id,
            @ApiParam(value = "Book object to be updated in database", required = true) @RequestBody Book book) {
        log.log(Level.INFO, "Calling updateBookById()");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBookById(id, book));
    }


    /**
     * Delete a book
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "Delete a book", response = Long.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted book by id"),
            @ApiResponse(code = 404, message = "Book wih specified id not found")})
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Long> deleteBookById(
            @ApiParam(value = "Id of book to be deleted from database", required = true) @PathVariable Long id) {
        log.log(Level.INFO, "Calling deleteBookById()");
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBookById(id));
    }

}
