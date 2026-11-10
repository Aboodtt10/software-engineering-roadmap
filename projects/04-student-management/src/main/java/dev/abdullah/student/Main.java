package dev.abdullah.student;
import java.util.Scanner;
public class Main {
    private static final StudentService service = new StudentService();
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n=== Student Management ===\n1. Add student\n2. List students\n3. Find student\n4. Remove student\n0. Exit");
                System.out.print("Choose: "); String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> addStudent(scanner);
                        case "2" -> service.findAll().forEach(System.out::println);
                        case "3" -> { int id = readId(scanner); System.out.println(service.findById(id).orElse(null)); }
                        case "4" -> { int id = readId(scanner); System.out.println(service.remove(id) ? "Student removed." : "Student not found."); }
                        case "0" -> running = false;
                        default -> System.out.println("Please choose a valid menu option.");
                    }
                } catch (IllegalArgumentException ex) { System.out.println("Error: " + ex.getMessage()); }
            }
        }
        System.out.println("Goodbye!");
    }
    private static void addStudent(Scanner scanner) {
        int id = readId(scanner);
        System.out.print("Name: "); String name = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Course: "); String course = scanner.nextLine();
        service.add(new Student(id, name, email, course)); System.out.println("Student added.");
    }
    private static int readId(Scanner scanner) {
        System.out.print("Student ID: ");
        try { return Integer.parseInt(scanner.nextLine().trim()); }
        catch (NumberFormatException ex) { throw new IllegalArgumentException("ID must be a whole number."); }
    }
}