# Sử dụng image hỗ trợ Java 22
FROM eclipse-temurin:22-jdk-alpine

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . .

# Cấp quyền thực thi cho Maven Wrapper (nếu sử dụng mvnw)
RUN chmod +x ./mvnw

# Cài đặt dependencies và build
RUN ./mvnw clean package -DskipTests

# Đặt tên file JAR ứng dụng
ARG JAR_FILE=target/lms-0.0.1-SNAPSHOT.jar

# Copy file JAR đã build vào container
COPY ${JAR_FILE} lms-0.0.1-SNAPSHOT.jar

# Cấu hình cổng mà ứng dụng sẽ sử dụng
EXPOSE 8080

# Lệnh để chạy ứng dụng
CMD ["java", "-jar", "lms-0.0.1-SNAPSHOT.jar"]
