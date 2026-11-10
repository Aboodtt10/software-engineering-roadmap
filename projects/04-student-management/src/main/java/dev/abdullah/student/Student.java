package dev.abdullah.student;
public record Student(int id, String name, String email, String course) {
    public Student {
        if (id <= 0) throw new IllegalArgumentException("Student ID must be positive.");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required.");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("A valid email is required.");
        if (course == null || course.isBlank()) throw new IllegalArgumentException("Course is required.");
    }
}