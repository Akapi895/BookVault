# Su dung image ho tro Java 22
FROM eclipse-temurin:22-jdk-alpine

# Thiet lap thu muc lam viec
WORKDIR /app

# Copy toan bo ma nguon vao contaner
COPY . .

# Cap quyen thuc thi cho Maven Wrapper (neu  dung mvnw)
RUN chmod +x ./mvnw

# dependencies va build
RUN ./mvnw clean package -DskipTests

# Dat ten file JAR ung dung
ARG JAR_FILE=target/lms-0.0.1-SNAPSHOT.jar

# Copy file JAR build vao image cuoi cung
COPY ${JAR_FILE} app.jar

# Cau hinh cong
EXPOSE 8080

# Lenh de chay
CMD ["java", "-jar", "app.jar"]
