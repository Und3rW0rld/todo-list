# Todo List Application

This is a RESTful API for managing a simple Todo List application using Spring Boot, Hibernate, and MySQL. The application allows users to create, read, update, and delete todo items. Each item includes a title and a description.

## Project Configuration

- **Framework**: Spring Boot 3.3.4
- **JDK**: OpenJDK 23
- **Java Version**: 17
- **Database Management System**: MySQL 8.0.39
- **JDBC Driver**: MySQL Connector/J (mysql-connector-j-8.2.0)

## Features

- **CRUD Operations**: Create, read, update, and delete Todo items.
- **RESTful Endpoints**: Provides endpoints for managing Todo items.
- **Database Integration**: Uses MySQL for data persistence.
- **ORM**: Uses Hibernate for object-relational mapping.
- **Validation**: Ensures data integrity with validation rules.

## Prerequisites

- Java 17 (OpenJDK 23)
- MySQL 8.0.39 or later
- Maven 3.8+ for dependency management

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Und3rW0rld/todo-list.git
   cd todo-list
   ```

2. **Configure MySQL Database**:
   - Create a MySQL database named `todo_db`.
   - Update the `src/main/resources/application.yml` with your database credentials:
     ```yaml
     spring:
       datasource:
         url: jdbc:mysql://localhost:3306/todo_db
         username: your-username
         password: your-password
       jpa:
         hibernate:
           ddl-auto: update
         show-sql: true
     ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
   - The application runs on `http://localhost:8080/` by default.
   - You can test the endpoints using tools like Postman or `curl`.

## API Endpoints

| Method | Endpoint         | Description                |
|--------|------------------|----------------------------|
| GET    | `/api/todos`     | Get all todo items         |
| GET    | `/api/todos/{id}`| Get a specific todo item   |
| POST   | `/api/todos`     | Create a new todo item     |
| PUT    | `/api/todos/{id}`| Update an existing todo item |
| DELETE | `/api/todos/{id}`| Delete a specific todo item|

## Running Tests

Unit tests are included to ensure the functionality of the service and controller layers. To run the tests, use the following command:

```bash
mvn test
```

## Tech Stack

- **Spring Boot**: Simplifies Java development with a comprehensive framework.
- **Hibernate**: Handles ORM between Java classes and database tables.
- **MySQL**: Stores todo items and handles persistence.
- **JUnit & Mockito**: Used for unit testing and mocking dependencies.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgements

- Spring Boot documentation: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
- Hibernate documentation: [https://hibernate.org/](https://hibernate.org/)
- MySQL documentation: [https://dev.mysql.com/doc/](https://dev.mysql.com/doc/)

---

This README provides a clear overview of the project, its configuration, how to set it up, and the API details. Feel free to customize further according to your project specifics!
