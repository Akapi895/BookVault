# **BookVault: Ứng Dụng Quản Lý Đầu Sách**

## **Mục Lục**  
1. [Giới thiệu](#giới-thiệu)  
2. [Cài đặt và Cách sử dụng](#cài-đặt-và-cách-sử-dụng)  
3. [Tính năng](#tính-năng)  
4. [Giấy phép](#giấy-phép)  
5. [Liên hệ](#liên-hệ)  

---

## **Giới thiệu**  
**BookVault** là một ứng dụng web giúp bạn quản lý bộ sưu tập sách một cách hiệu quả. Dành cho thư viện, người yêu sách hoặc bất kỳ ai muốn theo dõi bộ sưu tập ngày càng lớn của mình, BookVault mang đến giải pháp tổ chức và quản lý thông tin sách đơn giản và thuận tiện.  

### **Lợi ích:**  
- Dễ dàng tổ chức sách theo tiêu đề, tác giả, thể loại, và nhiều tiêu chí khác.  
- Theo dõi tình trạng sách, bao gồm sẵn có hoặc đang mượn.  
- Tăng năng suất với hệ thống quản lý tập trung.  

---

## **Cài đặt và Cách sử dụng**  
### **Yêu cầu hệ thống**  
Đảm bảo hệ thống của bạn đã cài đặt:  
- Java Development Kit (JDK)  
- Node.js  
- Hệ quản trị cơ sở dữ liệu (ví dụ: MySQL, PostgreSQL)  
- Trình duyệt web hiện đại  

### **Hướng dẫn cài đặt**  
1. **Clone kho lưu trữ:**  
   ```bash
   git clone https://github.com/Akapi895/CSDL17
### Đi tới thư mục dự án:
```bash
cd LmsApplication
```

### Thiết lập cơ sở dữ liệu:
1. Cấu hình các thông tin cơ sở dữ liệu trong file `application.properties`.
2. Chạy script SQL được cung cấp để khởi tạo cơ sở dữ liệu.

### Chạy server backend:
```bash
java -jar lmsApplication.jar
```

### Chạy server frontend:
```bash
npm install  
npm start
```

### Truy cập ứng dụng web:
Mở trình duyệt và truy cập địa chỉ:
```
http://localhost:8080
```

## Tính năng
- **Quản lý danh mục sách**: Thêm, sửa, xóa thông tin sách.
- **Tìm kiếm và Lọc**: Dễ dàng tìm sách qua từ khóa, thể loại, tác giả hoặc trạng thái.
- **Quản lý người dùng**: Hỗ trợ phân quyền người dùng (quản trị viên, nhân viên, người dùng).
- **Thiết kế đáp ứng**: Sử dụng tốt trên máy tính, máy tính bảng và điện thoại.
- **Bảo mật cơ sở dữ liệu**: Bảo vệ dữ liệu với hệ thống xác thực và mã hóa.
- **Một số hình ảnh về Web**:
  - **User**:
![Image 1](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/1.png)
![Image 2](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/2.png)
![Image 3](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/3.png)
![Image 4](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/4.png)
![Image 5](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/5.png)
![Image 6](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/6.png)
![Image 7](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/7.png)
![Image 8](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/8.png)
![Image 9](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/9.png)
![Image 10](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/10.png)
![Image 11](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/11.png)
  - **Admin**:
    ![Image 1](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/1.png)
    ![Image 2](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/2.png)
    ![Image 3](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/3.png)
    ![Image 4](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/4.png)
    ![Image 5](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/5.png)
    ![Image 6](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/6.png)

## Giấy phép
Dự án này được cấp phép theo giấy phép MIT. Vui lòng xem file `LICENSE` để biết thêm chi tiết.

## Liên hệ
Nếu bạn có câu hỏi, góp ý hoặc phản hồi, vui lòng liên hệ:

- **Nhà phát triển**: [3 Anh Em Siêu Nhân]
- **Email**: khiemlistss@gmail.com
- **GitHub**: [Akapi895]

Cảm ơn bạn đã sử dụng BookVault!
