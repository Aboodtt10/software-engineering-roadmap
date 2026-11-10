package dev.abdullah.student;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * In-memory application service for student CRUD operations.
 * The class is deliberately independent from console input/output.
 */
public class StudentService {
    private final Map<Integer, Student> students = new LinkedHashMap<>();

    public Student add(Student student) {
        Objects.requireNonNull(student, "student");
        if (students.containsKey(student.id())) {
            throw new IllegalArgumentException("A student with this ID already exists.");
        }
        students.put(student.id(), student);
        return student;
    }

    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> findAll() {
        return List.copyOf(students.values());
    }

    public Student update(int id, String name, String email, String course) {
        if (!students.containsKey(id)) {
            throw new IllegalArgumentException("Student not found.");
        }
        Student updated = new Student(id, name, email, course);
        students.put(id, updated);
        return updated;
    }

    public boolean remove(int id) {
        return students.remove(id) != null;
    }
}
