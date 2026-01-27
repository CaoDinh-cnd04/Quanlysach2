import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Chương trình menu quản lý sách
 */
public class BookManagement {
    private static List<Book> listBook = new ArrayList<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    
    public static void main(String[] args) {
        // Set UTF-8 encoding cho console
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setProperty("console.encoding", "UTF-8");
        } catch (Exception e) {
            // Nếu không set được encoding, vẫn chạy bình thường
        }
        int choice;
        do {
            showMenu();
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự xuống dòng
            
            switch (choice) {
                case 1 -> addBook();
                case 2 -> deleteBook();
                case 3 -> updateBook();
                case 4 -> displayAllBooks();
                case 5 -> findBooksByTitle();
                case 6 -> getBooksByPriceLimit();
                case 7 -> getBooksByAuthors();
                case 0 -> System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                default -> System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
            
            if (choice != 0) {
                System.out.println("\nNhấn Enter để tiếp tục...");
                scanner.nextLine();
            }
        } while (choice != 0);
        
        scanner.close();
    }
    
    /**
     * Hiển thị menu
     */
    private static void showMenu() {
        System.out.println("\n=========================================");
        System.out.println("    HỆ THỐNG QUẢN LÝ SÁCH");
        System.out.println("=========================================");
        System.out.println("1. Thêm 1 cuốn sách");
        System.out.println("2. Xóa 1 cuốn sách");
        System.out.println("3. Thay đổi cuốn sách (Update)");
        System.out.println("4. Xuất thông tin tất cả các cuốn sách");
        System.out.println("5. Tìm cuốn sách có tựa đề chứa chữ 'Lập trình'");
        System.out.println("6. Lấy sách: Nhập K và giá P, lấy tối đa K cuốn sách có giá <= P");
        System.out.println("7. Tìm sách theo danh sách tác giả");
        System.out.println("0. Thoát");
        System.out.println("=========================================");
    }
    
    /**
     * 1. Thêm 1 cuốn sách
     */
    private static void addBook() {
        System.out.println("\n--- THÊM SÁCH MỚI ---");
        System.out.print("Nhập tên sách: ");
        String title = scanner.nextLine();
        
        System.out.print("Nhập tác giả: ");
        String author = scanner.nextLine();
        
        System.out.print("Nhập đơn giá: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        Book book = new Book(nextId++, title, author, price);
        listBook.add(book);
        System.out.println("✓ Đã thêm sách thành công!");
        book.output();
    }
    
    /**
     * 2. Xóa 1 cuốn sách
     */
    private static void deleteBook() {
        System.out.println("\n--- XÓA SÁCH ---");
        System.out.print("Nhập mã sách cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        boolean removed = listBook.removeIf(book -> book.getId() == id);
        
        if (removed) {
            System.out.println("✓ Đã xóa sách có mã " + id + " thành công!");
        } else {
            System.out.println("✗ Không tìm thấy sách có mã " + id);
        }
    }
    
    /**
     * 3. Thay đổi cuốn sách (Update)
     */
    private static void updateBook() {
        System.out.println("\n--- CẬP NHẬT SÁCH ---");
        System.out.print("Nhập mã sách cần cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Book book = listBook.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
        
        if (book == null) {
            System.out.println("✗ Không tìm thấy sách có mã " + id);
            return;
        }
        
        System.out.println("Thông tin sách hiện tại:");
        book.output();
        
        System.out.print("Nhập tên sách mới (Enter để giữ nguyên): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            book.setTitle(title);
        }
        
        System.out.print("Nhập tác giả mới (Enter để giữ nguyên): ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) {
            book.setAuthor(author);
        }
        
        System.out.print("Nhập đơn giá mới (0 để giữ nguyên): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        if (price > 0) {
            book.setPrice(price);
        }
        
        System.out.println("✓ Đã cập nhật sách thành công!");
        book.output();
    }
    
    /**
     * 4. Xuất thông tin tất cả các cuốn sách
     */
    private static void displayAllBooks() {
        System.out.println("\n--- DANH SÁCH TẤT CẢ SÁCH ---");
        if (listBook.isEmpty()) {
            System.out.println("Danh sách sách trống!");
        } else {
            System.out.println("Tổng số sách: " + listBook.size());
            listBook.forEach(Book::output);
        }
    }
    
    /**
     * 5. Tìm cuốn sách có tựa đề chứa chữ "Lập trình" và không phân biệt hoa thường
     * Sử dụng Stream như trong hướng dẫn
     */
    private static void findBooksByTitle() {
        System.out.println("\n--- TÌM SÁCH THEO TỰA ĐỀ ---");
        System.out.print("Nhập từ khóa tìm kiếm (mặc định: 'Lập trình'): ");
        String input = scanner.nextLine();
        
        final String keyword = input.isEmpty() ? "Lập trình" : input;
        
        List<Book> list5 = listBook.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        
        if (list5.isEmpty()) {
            System.out.println("✗ Không tìm thấy sách nào có tựa đề chứa '" + keyword + "'");
        } else {
            System.out.println("Tìm thấy " + list5.size() + " cuốn sách:");
            list5.forEach(Book::output);
        }
    }
    
    /**
     * 6. Lấy tối đa K cuốn sách có giá <= P
     * Sử dụng Stream với filter và limit
     */
    private static void getBooksByPriceLimit() {
        System.out.println("\n--- TÌM SÁCH THEO GIÁ ---");
        System.out.print("Nhập số lượng sách tối đa (K): ");
        int k = scanner.nextInt();
        
        System.out.print("Nhập giá sách tối đa (P): ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        
        List<Book> result = listBook.stream()
                .filter(book -> book.getPrice() <= price)
                .limit(k)
                .toList();
        
        if (result.isEmpty()) {
            System.out.println("✗ Không tìm thấy sách nào có giá <= " + price);
        } else {
            System.out.println("Tìm thấy " + result.size() + " cuốn sách (tối đa " + k + " cuốn):");
            result.forEach(Book::output);
        }
    }
    
    /**
     * 7. Tìm tất cả cuốn sách của các tác giả trong danh sách
     * Sử dụng Stream với filter và Set
     */
    private static void getBooksByAuthors() {
        System.out.println("\n--- TÌM SÁCH THEO TÁC GIẢ ---");
        System.out.print("Nhập số lượng tác giả: ");
        int numAuthors = scanner.nextInt();
        scanner.nextLine();
        
        Set<String> authors = new HashSet<>();
        System.out.println("Nhập danh sách tác giả:");
        for (int i = 0; i < numAuthors; i++) {
            System.out.print("Tác giả " + (i + 1) + ": ");
            String author = scanner.nextLine();
            authors.add(author);
        }
        
        // Chuyển sang Set lowercase để so sánh không phân biệt hoa thường
        Set<String> authorsLower = authors.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        
        List<Book> result = listBook.stream()
                .filter(book -> authorsLower.contains(book.getAuthor().toLowerCase()))
                .toList();
        
        if (result.isEmpty()) {
            System.out.println("✗ Không tìm thấy sách nào của các tác giả: " + authors);
        } else {
            System.out.println("Tìm thấy " + result.size() + " cuốn sách của các tác giả:");
            result.forEach(Book::output);
        }
    }
}
