/**
 * Lớp Book để lưu trữ thông tin sách
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    
    // Constructor mặc định
    public Book() {
    }
    
    // Constructor đầy đủ tham số
    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    // Constructor không có id (id sẽ được tự động tăng)
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    // Getter và Setter
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Phương thức xuất thông tin sách
     */
    public void output() {
        System.out.println("Mã Sách: " + id + 
                          ", Tên Sách: " + title + 
                          ", Tác giả: " + author + 
                          ", Đơn giá: " + price);
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
