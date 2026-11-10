package dev.abdullah.student;
import java.util.*;
public class StudentService {
    private final Map<Integer, Student> students = new LinkedHashMap<>();
    public Student add(Student student) {
        Objects.requireNonNull(student, "student");
        if (students.containsKey(student.id())) throw new IllegalArgumentException("A student with this ID already exists.");
        students.put(student.id(), student); return student;
    }
    public Optional<Student> findById(int id) { return Optional.ofNullable(students.get(id)); }
    public List<Student> findAll() { return List.copyOf(students.values()); }
    public boolean remove(int id) { return students.remove(id) != null; }
}