# Student Management System

A Java 17 console application demonstrating object-oriented design, records, collections, validation, and separation of responsibilities.

## Features

- Add students with ID, name, email, and course.
- List all students.
- Find a student by ID.
- Remove a student by ID.
- Validate input and prevent duplicate IDs.

## Requirements

- JDK 17 or newer
- Apache Maven 3.8+

## Run

From this directory:

```bash
mvn clean package
mvn exec:java -Dexec.mainClass="dev.abdullah.student.Main"
```

If the Maven exec plugin is not available locally, compile and run with the JDK:

```bash
mvn clean package
java -cp target/classes dev.abdullah.student.Main
```

## Design

- `Student` is an immutable Java record containing validated student data.
- `StudentService` owns the in-memory collection and student operations.
- `Main` handles the console menu and user input.

## Learning checklist

- [ ] Explain why the `Student` record is immutable.
- [ ] Explain why `LinkedHashMap` is used.
- [ ] Add an update-student operation.
- [ ] Add file or database persistence.
- [ ] Write unit tests for `StudentService`.

## Current limitations

Data is held in memory and is lost when the program exits. This project does not yet use a database, graphical interface, or web API.
