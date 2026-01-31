package phattrienungdung2ee.bai2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import phattrienungdung2ee.bai2.model.Book;
import phattrienungdung2ee.bai2.service.BookService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"", "/"})
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = {"", "/"})
    public String addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return "Book added successfully!";
    }

    @PutMapping(value = {"/{id}", "/{id}/"})
    public String updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return "Book updated successfully!";
    }

    @PatchMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<String> patchBook(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        if (bookService.patchBook(id, updates)) {
            return ResponseEntity.ok("Book patched successfully!");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "Book deleted successfully!";
    }
}
