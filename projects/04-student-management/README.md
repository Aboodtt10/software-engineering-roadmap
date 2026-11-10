# Student Management System

A Java 17 console application demonstrating object-oriented design, records, collections, validation, separation of concerns, and unit testing.

## Features

- Add, list, find, update, and remove students.
- Validate IDs, names, email addresses, and course names.
- Prevent duplicate student IDs.
- Keep console interaction separate from business logic.
- Unit tests for service behavior and validation using JUnit 5.

## Requirements

- JDK 17 or newer
- Apache Maven 3.8+

## Run

From this directory:

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="dev.abdullah.student.Main"
```

Run the automated tests:

```bash
mvn test
```

## Design

- `Student` is an immutable Java record containing validated student data.
- `StudentService` owns the in-memory collection and CRUD operations.
- `Main` handles console menus and user input.
- `StudentServiceTest` checks core behavior with JUnit 5.

## Learning checklist

- [ ] Explain why the `Student` record is immutable.
- [ ] Explain why `LinkedHashMap` is used.
- [ ] Explain how unit tests isolate the service layer.
- [ ] Add file or database persistence.
- [ ] Add a REST API with Spring Boot.
- [ ] Add integration tests.

## Current limitations

Data is held in memory and is lost when the program exits. This project does not yet use a database, graphical interface, or web API.
