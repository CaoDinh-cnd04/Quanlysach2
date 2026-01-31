package phattrienungdung2ee.bai2.service;

import org.springframework.stereotype.Service;
import phattrienungdung2ee.bai2.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public BookService() {
        // Dữ liệu mẫu sẵn khi khởi động
        books.add(new Book(1, "Tên nhẹ về đến", "Gabriel García Márquez"));
        books.add(new Book(2, "Nhà giả kim", "Paulo Coelho"));
        books.add(new Book(3, "Đắc nhân tâm", "Dale Carnegie"));
        books.add(new Book(4, "Lập trình Java", "Nguyễn Văn A"));
        books.add(new Book(5, "Spring Boot in Action", "Craig Walls"));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int id, Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                });
    }

    public void deleteBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    /**
     * Cập nhật một phần (PATCH): chỉ cập nhật các trường có trong map.
     */
    public boolean patchBook(int id, Map<String, Object> updates) {
        Book book = getBookById(id);
        if (book == null) return false;
        if (updates.containsKey("title") && updates.get("title") != null) {
            book.setTitle(updates.get("title").toString());
        }
        if (updates.containsKey("author") && updates.get("author") != null) {
            book.setAuthor(updates.get("author").toString());
        }
        return true;
    }
}
