package com.JPoP2.controller;

import com.JPoP2.entity.Library;
import com.JPoP2.model.Book;
import com.JPoP2.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/${api.version}/")
@Api(value = "Library Service")
public class LibraryController {
    private static final Logger LOG = Logger.getLogger(LibraryController.class.getName());

    @Autowired
    @Resource(name = "${api.version}")
    private com.JPoP2.service.LibraryService libraryService;

    @ApiOperation(value = "Login service", response = Map.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "If user is valid"),
            @ApiResponse(code = 401, message = "If username or password is invalid")})
    @PostMapping("/lib")
    public ResponseEntity<Map<String, String>> validateLogin(@RequestBody Map<String, String> payload) {
        LOG.log(Level.INFO, "Calling validateLogin()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.validateLogin(payload.get("emailId"), payload.get("password")));
    }

    @ApiOperation(value = "View a list of all the books available in library", response = List.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully retrieved all the books"))
    @GetMapping("/lib/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        LOG.log(Level.INFO, "Calling getAllBooks()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getAllBooks());
    }

    @ApiOperation(value = "View an book by id", response = Book.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved book by id"),
            @ApiResponse(code = 404, message = "Book with specified id not found")})
    @GetMapping("/lib/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        LOG.log(Level.INFO, "Calling getBookById()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getBookById(bookId));
    }

    @ApiOperation(value = "Add an book", response = Book.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = "Returns the newly added book"))
    @PostMapping("/lib/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        LOG.log(Level.INFO, "Calling addBook()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.addBook(book));
    }

    @ApiOperation(value = "Delete an book", response = Long.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted book by id"),
            @ApiResponse(code = 404, message = "Book wih specified id not found")})
    @DeleteMapping("/lib/books/{bookId}")
    public ResponseEntity<Long> deleteBookById(@PathVariable Long bookId) {
        LOG.log(Level.INFO, "Calling deleteBookById()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.deleteBookById(bookId));
    }

    @ApiOperation(value = "View a list of all the available users", response = List.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully retrieved all the users"))
    @GetMapping("/lib/users")
    public ResponseEntity<List<User>> getAllUsers() {
        LOG.log(Level.INFO, "Calling getAllUsers()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getAllUsers());
    }

    @ApiOperation(value = "View an user by id", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved user by id"),
            @ApiResponse(code = 404, message = "User with specified id not found")})
    @GetMapping("/lib/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        LOG.log(Level.INFO, "Calling getUserById()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getUserById(userId));
    }

    @ApiOperation(value = "Add an user", response = User.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = "Returns the newly added user"))
    @PostMapping("/lib/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        LOG.log(Level.INFO, "Calling addUser()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.addUser(user));
    }

    @ApiOperation(value = "Delete an user", response = Long.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully deleted user by id"),
            @ApiResponse(code = 404, message = "User wih specified id not found")})
    @DeleteMapping("/lib/users/{userId}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long userId) {
        LOG.log(Level.INFO, "Calling deleteUserById()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.deleteUserById(userId));
    }

    @ApiOperation(value = "Update an user", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Returns the updated user"),
            @ApiResponse(code = 404, message = "User wih specified id not found")})
    @PutMapping("/lib/users/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, User user) {
        LOG.log(Level.INFO, "Calling updateUserById()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.updateUserById(userId, user));
    }

    @ApiOperation(value = "Assign a book to the user", response = User.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Returns the library system response"))
    @PostMapping("/lib/users/{userId}/books/{bookId}")
    public ResponseEntity<Library> issueBookToUser(@PathVariable Long userId, @PathVariable Long bookId) {
        LOG.log(Level.INFO, "Calling issueBookToUser()");
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.issueBookToUser(userId, bookId));
    }
}
