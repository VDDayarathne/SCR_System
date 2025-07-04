# Student Course Registration System

A Java 17 Spring Boot backend for student course registration, featuring RESTful APIs, in-memory storage, validation, and clean architecture for managing students and courses.

---

## How to Run the Application

1. **Clone the repository:**
   ```bash
   git clone https://github.com/VDDayarathne/SCR_System.git
   cd SCR_System
   ```

2. **Build and run the application (Maven):**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The API will be available at: [http://localhost:8080](http://localhost:8080)

3. **Open Swagger UI for API documentation:**  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
   or  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Sample HTTP Requests/Responses

### Register a New Student
**Request:**
```bash
curl -X POST "http://localhost:8080/students" \
     -H "Content-Type: application/json" \
     -d '{"name": "Alice", "email": "alice@example.com"}'
```
**Response:**
```json
{
  "id": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "name": "Alice",
  "email": "alice@example.com"
}
```

### Add a New Course
**Request:**
```bash
curl -X POST "http://localhost:8080/courses" \
     -H "Content-Type: application/json" \
     -d '{"code": "CS101", "title": "Intro to CS", "instructor": "Dr. Smith"}'
```
**Response:**
```json
{
  "id": "c56a4180-65aa-42ec-a945-5fd21dec0538",
  "code": "CS101",
  "title": "Intro to CS",
  "instructor": "Dr. Smith"
}
```

### Register Student for a Course
**Request:**
```bash
curl -X POST "http://localhost:8080/students/{studentId}/register/{courseId}"
```
**Response:**
```json
{
  "studentId": "f47ac10b-58cc-4372-a567-0e02b2c3d479",
  "courseId": "c56a4180-65aa-42ec-a945-5fd21dec0538",
  "registeredAt": "2025-06-21T17:40:00"
}
```

---

## Assumptions

- All data is stored in-memory; state is reset upon application restart.
- UUIDs are used for Student and Course IDs.
- Student email and Course code are globally unique.
- No seat limits per course.
- Validation and error responses follow RESTful standards.
- Registration and drop endpoints enforce all business rules.
- No authentication or authorization.

---

## Code Structure

```
src/main/java/com/project/scrsystem/
│
├── config/          # Swagger/OpenAPI configuration
├── controller/      # REST API controllers
├── dto/             # Data Transfer Objects (requests/responses)
├── exception/       # Custom exceptions & global handler
├── model/           # Domain models (Student, Course, Registration)
├── repository/      # In-memory data storage
├── service/         # Business logic services
└── Main.java        # Application entry point
```

---

## Test Coverage Summary

- Unit and integration tests are provided for key service and controller logic.
- Test cases cover:
  - Student & Course creation
  - Registration and drop operations
  - Business rule enforcement and error handling
- Run all tests with:
  ```bash
  mvn test
  ```
- 
![Screenshot 2025-06-22 013621](https://github.com/user-attachments/assets/b4ab498b-bdbf-4eb8-96f5-f25b74a4eb16)
---

## API Documentation

- Swagger UI is available at `/swagger-ui.html` or `/swagger-ui/index.html` after starting the application.
- 
![Screenshot 2025-06-22 014833](https://github.com/user-attachments/assets/1e451643-5f0d-45ae-9b47-a77d154df47b)
---

## Author

- [VDDayarathne](https://github.com/VDDayarathne)
