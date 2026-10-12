# Java Backend -- Simple Handwritten-Style Notes

## 1. Pydantic Model

- Pydantic validates input data.
- An invalid request body in FastAPI usually gives **422 Unprocessable
  Entity**.
- Java equivalent: `@Valid`, `@NotBlank`.

## 2. SQL Placeholders

- `?` and `%s` pass values safely into SQL queries.
- They help prevent SQL injection.
- Never directly add user input using an f-string.

## 3. Primary Key and Foreign Key

- **Primary Key:** Uniquely identifies each row in a table.
- **Foreign Key:** Connects two tables.

## 4. ORM

- ORM connects objects in a program with database tables.
- **JPA:** Defines the rules for ORM in Java.
- **Hibernate:** Implements JPA.

## 5. CORS

- CORS controls cross-origin requests made by web browsers.
- It does not normally affect Postman, `curl`, or backend-to-backend
  requests.

## 6. Environment Variable

- Stores configuration outside the source code.
- Avoids hardcoding database URLs and passwords.
- Example: `spring.datasource.url=${DB_URL}`

## 7. Dependency Injection (DI)

- DI means providing required objects to a class instead of creating
  them inside the class.
- **Spring Boot:** Constructor injection.

## 8. Separation of Concerns

- Each class should have a separate responsibility.
- Example: Controller handles requests, Service handles logic, and
  Repository handles database operations.

## 9. Java Layers

- **Controller:** Handles HTTP requests and responses.
- **Service:** Contains business logic.
- **Repository:** Performs database operations.
- **Entity:** Represents database table data.

## 10. Run JAR on Another Port

Command:

```bash
java -jar app.jar --server.port=9090
```

- Runs the application on port `9090`.
- No need to edit `application.properties`.

## 11. SOLID Principle

- **S -- Single Responsibility Principle**
- A class should have one main responsibility.
- Example: `UserService` handles user business logic.

## 12. Lombok `@Data`

- Automatically generates getters, setters, `toString()`, `equals()`,
  and `hashCode()`.

## 13. ResponseEntity

```java
return ResponseEntity.notFound().build();
```

- Returns **404 Not Found**.
- Uses the Builder pattern.
- Method chaining makes the code readable and creates the response
  step by step.


- **Controller** → Request
- **Service** → Logic
- **Repository** → Database
- **Entity** → Data
