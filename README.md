# Hệ thống Quản lý Sách - Java Console

Chương trình menu quản lý sách được viết bằng Java thuần túy, không sử dụng Spring Boot.

## Cấu trúc Project

```
src/main/java/com/example/bookmanagement/
├── Book.java              # Entity class cho sách
└── BookManagement.java    # Main class với menu console
```

## Yêu cầu

- Java 17 hoặc cao hơn
- JDK đã được cài đặt và cấu hình trong PATH

## Cách chạy chương trình

### Cách 1: Compile và chạy bằng javac/java

```bash
# Di chuyển vào thư mục src/main/java
cd src/main/java

# Compile các file Java
javac com/example/bookmanagement/Book.java com/example/bookmanagement/BookManagement.java

# Chạy chương trình
java com.example.bookmanagement.BookManagement
```

### Cách 2: Sử dụng VS Code

1. Mở file `BookManagement.java`
2. Click phải vào file → **Run Java** hoặc nhấn `F5`
3. Hoặc mở Terminal và chạy:
   ```bash
   cd src/main/java
   javac com/example/bookmanagement/Book.java com/example/bookmanagement/BookManagement.java
   java com.example.bookmanagement.BookManagement
   ```

### Cách 3: Sử dụng IntelliJ IDEA / Eclipse

1. Mở project trong IDE
2. Tìm file `BookManagement.java`
3. Click phải → **Run 'BookManagement.main()'**

## Các chức năng

### 1. Thêm 1 cuốn sách
- Nhập thông tin: Tên sách, Tác giả, Đơn giá
- Mã sách sẽ tự động tăng

### 2. Xóa 1 cuốn sách
- Nhập mã sách cần xóa

### 3. Thay đổi cuốn sách (Update)
- Nhập mã sách cần cập nhật
- Có thể cập nhật từng trường hoặc giữ nguyên

### 4. Xuất thông tin tất cả các cuốn sách
- Hiển thị danh sách tất cả sách trong hệ thống

### 5. Tìm sách có tựa đề chứa "Lập trình" (không phân biệt hoa thường)
- Sử dụng Java Stream API với `filter` và `toLowerCase()`
- Mặc định tìm từ khóa "Lập trình" hoặc nhập từ khóa khác

### 6. Lấy tối đa K cuốn sách có giá <= P
- Nhập số lượng K và giá P
- Sử dụng Java Stream API với `filter` và `limit`

### 7. Tìm sách theo danh sách tác giả
- Nhập số lượng tác giả và danh sách tác giả
- Sử dụng Java Stream API với `filter` và `Set`

## Ví dụ sử dụng

```
=========================================
    HỆ THỐNG QUẢN LÝ SÁCH
=========================================
1. Thêm 1 cuốn sách
2. Xóa 1 cuốn sách
3. Thay đổi cuốn sách (Update)
4. Xuất thông tin tất cả các cuốn sách
5. Tìm cuốn sách có tựa đề chứa chữ 'Lập trình'
6. Lấy sách: Nhập K và giá P, lấy tối đa K cuốn sách có giá <= P
7. Tìm sách theo danh sách tác giả
0. Thoát
=========================================
Nhập lựa chọn của bạn: 1

--- THÊM SÁCH MỚI ---
Nhập tên sách: Lập trình Java
Nhập tác giả: Nguyễn Văn A
Nhập đơn giá: 150000
✓ Đã thêm sách thành công!
Mã Sách: 1, Tên Sách: Lập trình Java, Tác giả: Nguyễn Văn A, Đơn giá: 150000.0
```

## Lưu ý

- Dữ liệu được lưu trong bộ nhớ (List), sẽ mất khi thoát chương trình
- Tất cả các chức năng tìm kiếm (5, 6, 7) sử dụng Java Stream API như trong hướng dẫn
- Chức năng 5: Sử dụng `filter` với `toLowerCase()` và `contains()`
- Chức năng 6: Sử dụng `filter` kết hợp với `limit`
- Chức năng 7: Sử dụng `filter` với Set để tìm kiếm tác giả

## Công nghệ sử dụng

- Java 17+
- Java Stream API
- Scanner cho input từ bàn phím
- ArrayList để lưu trữ dữ liệu
