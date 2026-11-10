package dev.abdullah.student;

import java.util.Scanner;

public class Main {
    private static final StudentService service = new StudentService();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                System.out.print("Choose: ");
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> addStudent(scanner);
                        case "2" -> service.findAll().forEach(System.out::println);
                        case "3" -> findStudent(scanner);
                        case "4" -> removeStudent(scanner);
                        case "5" -> updateStudent(scanner);
                        case "0" -> running = false;
                        default -> System.out.println("Please choose a valid menu option.");
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("\n=== Student Management ===");
        System.out.println("1. Add student");
        System.out.println("2. List students");
        System.out.println("3. Find student");
        System.out.println("4. Remove student");
        System.out.println("5. Update student");
        System.out.println("0. Exit");
    }

    private static void addStudent(Scanner scanner) {
        int id = readId(scanner);
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Course: ");
        String course = scanner.nextLine();
        service.add(new Student(id, name, email, course));
        System.out.println("Student added.");
    }

    private static void findStudent(Scanner scanner) {
        int id = readId(scanner);
        System.out.println(service.findById(id).orElse(null));
    }

    private static void removeStudent(Scanner scanner) {
        int id = readId(scanner);
        System.out.println(service.remove(id) ? "Student removed." : "Student not found.");
    }

    private static void updateStudent(Scanner scanner) {
        int id = readId(scanner);
        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New email: ");
        String email = scanner.nextLine();
        System.out.print("New course: ");
        String course = scanner.nextLine();
        service.update(id, name, email, course);
        System.out.println("Student updated.");
    }

    private static int readId(Scanner scanner) {
        System.out.print("Student ID: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("ID must be a whole number.");
        }
    }
}
