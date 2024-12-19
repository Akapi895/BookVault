# **BookVault: Ứng Dụng Quản Lý Đầu Sách**

## **Mục Lục**  
1. [Giới thiệu](#giới-thiệu)  
2. [Cài đặt và Cách sử dụng](#cài-đặt-và-cách-sử-dụng)  
3. [Tính năng](#tính-năng)  
 

---

## **Giới thiệu**  
**BookVault** là một ứng dụng web giúp bạn quản lý bộ sưu tập sách một cách hiệu quả. Dành cho thư viện, người yêu sách hoặc bất kỳ ai muốn theo dõi bộ sưu tập ngày càng lớn của mình, BookVault mang đến giải pháp tổ chức và quản lý thông tin sách đơn giản và thuận tiện. 

### **Thành viên:**
   **1.** Phạm Anh Tuấn - MSV: 23021709<br>
   **2.** Trương Mạnh Khiêm - MSV: 23021601<br>
   **3.** Trần Chiến Thắng - MSV: 23021725<br>

### **Lợi ích:**  
- Dễ dàng tổ chức sách theo tiêu đề, tác giả, thể loại, và nhiều tiêu chí khác.  
- Theo dõi tình trạng sách, bao gồm sẵn có hoặc đang mượn.  
- Tăng năng suất với hệ thống quản lý tập trung.  

### **Mô hình:**  
![Image 0](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/database.png)

---

## **Cài đặt và Cách sử dụng**  
### **Yêu cầu hệ thống**  
Đảm bảo hệ thống của bạn đã cài đặt:  
- Java Development Kit (JDK)  
- Hệ quản trị cơ sở dữ liệu (MySQL Workbench)  
- IntellIJ 

### **Hướng dẫn cài đặt**  
1. **Clone ứng dụng về máy:**  
   ```bash
   git clone https://github.com/Akapi895/BookVault

2. **Thiết lập cơ sở dữ liệu:**
a. Tạo Schemas mới tên "lms" trong MySQL Workbench -> File -> Open SQL Script... -> Chọn file lms.sql trong folder database của dự án -> Chọn Schemas 'lms' -> chạy toàn bộ đoạn lệnh SQL từ file lms.sql<br>
b. Cấu hình các thông tin về MySQL Connections trong file `application.properties`. (username:..., password:...)<br>

3. **Truy cập ứng dụng web:**
a. Mở folder BookVault trong IntellIJ và chạy LmsApplication.java<br>
b. Mở trình duyệt và truy cập địa chỉ:
```
http://localhost:8080
```

### Tính năng
- **Quản lý danh mục sách**: Thêm, sửa, xóa thông tin sách.
- **Tìm kiếm và Lọc**: Dễ dàng tìm sách qua từ khóa, thể loại, tác giả hoặc trạng thái.
- **Quản lý người dùng**: Hỗ trợ phân quyền người dùng (quản trị viên, nhân viên, người dùng).
- **Thiết kế đáp ứng**: Sử dụng tốt trên máy tính, máy tính bảng và điện thoại.
- **Bảo mật cơ sở dữ liệu**: Bảo vệ dữ liệu với hệ thống xác thực và mã hóa.
- **Một số hình ảnh về Web**:
  - **User**:
![Image 1](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/1.png)
![Image 3](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/3.png)
![Image 6](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/6.png)
![Image 10](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/user/10.png)

  - **Admin**:
    ![Image 1](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/1.png)
    ![Image 2](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/2.png)
    ![Image 3](https://github.com/Akapi895/CSDL17/blob/main/asset/frontend/admin/3.png)
  

Cảm ơn bạn đã sử dụng BookVault!
