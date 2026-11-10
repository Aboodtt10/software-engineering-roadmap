package dev.abdullah.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    void addsAndFindsStudent() {
        Student student = new Student(1, "Abdullah", "abdullah@example.com", "Software Engineering");
        service.add(student);
        assertEquals(student, service.findById(1).orElseThrow());
    }

    @Test
    void rejectsDuplicateIds() {
        service.add(new Student(1, "A", "a@example.com", "SE"));
        assertThrows(IllegalArgumentException.class,
                () -> service.add(new Student(1, "B", "b@example.com", "CS")));
    }

    @Test
    void rejectsInvalidStudentData() {
        assertThrows(IllegalArgumentException.class,
                () -> new Student(0, "Abdullah", "abdullah@example.com", "SE"));
        assertThrows(IllegalArgumentException.class,
                () -> new Student(2, " ", "abdullah@example.com", "SE"));
        assertThrows(IllegalArgumentException.class,
                () -> new Student(3, "Abdullah", "not-an-email", "SE"));
    }

    @Test
    void updatesExistingStudent() {
        service.add(new Student(1, "Old Name", "old@example.com", "SE"));
        Student updated = service.update(1, "New Name", "new@example.com", "Computer Science");
        assertEquals("New Name", updated.name());
        assertEquals("new@example.com", service.findById(1).orElseThrow().email());
    }

    @Test
    void doesNotUpdateMissingStudent() {
        assertThrows(IllegalArgumentException.class,
                () -> service.update(99, "Name", "name@example.com", "SE"));
    }

    @Test
    void returnsImmutableStudentList() {
        service.add(new Student(1, "Abdullah", "abdullah@example.com", "SE"));
        assertThrows(UnsupportedOperationException.class,
                () -> service.findAll().clear());
    }

    @Test
    void removesStudent() {
        service.add(new Student(1, "Abdullah", "abdullah@example.com", "SE"));
        assertTrue(service.remove(1));
        assertTrue(service.findById(1).isEmpty());
        assertFalse(service.remove(1));
    }
}
