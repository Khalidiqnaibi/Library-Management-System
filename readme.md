# Library Management System

A REST API for managing a library's authors, categories, books, and borrowers — built with Spring Boot and PostgreSQL as part of the CoTeDe Learning Phase project.

## Tech Stack

- **Java 26**
- **Spring Boot 4.1.0**
- **Spring Data JPA**
- **Spring Boot Validation**
- **PostgreSQL**
- **Gradle**

## Prerequisites

- JDK 26
- PostgreSQL installed and running
- (Optional) IntelliJ IDEA, pgAdmin, Postman

## Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/Khalidiqnaibi/Library-Management-System.git
   cd Library-Management-System
   ```

2. **Create a PostgreSQL database**
   ```sql
   CREATE DATABASE library_management_system;
   ```

3. **Add the PostgreSQL driver**

   The project currently only declares the JPA starter. Add the PostgreSQL driver to `build.gradle`:
   ```gradle
   runtimeOnly 'org.postgresql:postgresql'
   ```

4. **Configure the database connection**

   Update `src/main/resources/application.yaml` with your database credentials:
   ```yaml
   spring:
     application:
       name: LibraryManagementSystem
     datasource:
       url: jdbc:postgresql://localhost:5432/library_management_system
       username: your_username
       password: your_password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
   ```

5. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

   The API will be available at `http://localhost:8080`.

## Project Structure

```
src/main/java/immortal/librarymanagementsystem/
├── Controllers/    # REST endpoints
├── Services/       # Business logic (interfaces + impl)
├── Repositories/   # Spring Data JPA repositories
├── Entities/       # JPA entities (Author, Book, Category, Borrower)
├── DTOs/           # Request/response DTOs per entity
└── Exceptions/     # Custom exceptions + global exception handler
```

The app follows a layered architecture (Controller → Service → Repository) with constructor injection throughout.

## API Overview

### Authors — `/api/authors`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/authors` | Create an author |
| GET    | `/api/authors` | List all authors |
| GET    | `/api/authors/{id}` | Get an author by ID |
| PUT    | `/api/authors/{id}` | Update an author |
| DELETE | `/api/authors/{id}` | Delete an author |

### Categories — `/api/categorys`
Same CRUD shape as Authors.

### Borrowers — `/api/borrowers`
Same CRUD shape as Authors.

### Books — `/api/books`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/books` | Create a book |
| GET    | `/api/books` | List all books |
| GET    | `/api/books/{id}` | Get a book by ID |
| PUT    | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |
| PUT    | `/api/books/{bookId}/borrow/{borrowerId}` | Borrow a book |
| PUT    | `/api/books/{bookId}/return` | Return a borrowed book |
| GET    | `/api/books/available` | List only available (unborrowed) books |
| GET    | `/api/books/filter/category/{categoryId}` | Filter books by category |
| GET    | `/api/books/filter/author/{authorId}` | Filter books by author |
| GET    | `/api/books/filter/{title}` | Search books by title |

## Business Rules

- A book cannot be borrowed if it's already borrowed (`BookAlreadyBorrowedException`).
- A book cannot be returned if it isn't currently borrowed (`BookNotBorrowed`).
- Referencing a non-existent author, category, book, or borrower throws `ResourceNotFoundException`.

## Validation

All request DTOs are validated with Jakarta Bean Validation (`@NotBlank`, `@Size`, `@NotNull`, `@Positive`), and validation failures return a `400` with a field-to-message error map via the global exception handler.

## Error Handling

A `@RestControllerAdvice`-based `GlobalExceptionHandler` maps exceptions to structured JSON error responses:

| Exception | Response |
|---|---|
| `ResourceNotFoundException` | `{ "error": "..." }` |
| `BookAlreadyBorrowedException` | `{ "error": "..." }` |
| `BookNotBorrowed` | `{ "error": "..." }` |
| `MethodArgumentNotValidException` | `{ "field": "message", ... }` |

## Sample Data

The database should be seeded with at least:
- 5 authors
- 5 categories
- 20 books
- 10 borrowers

*(Seeding not yet automated — add sample records manually via the API, pgAdmin, or a `data.sql` file.)*

## Postman Collection

*(Not yet included — add an exported Postman collection here, organized into folders by feature: Authors, Categories, Borrowers, Books.)*

## Testing

```bash
./gradlew test
```

## Author
### Khalid Afif Iqnaibi

Prepared as part of the CoTeDe Technologies Learning Phase.