package com.revature.crs.Student;

import java.util.Scanner;

public class StudentController {
    public Scanner scanner;
    private final StudentService studentService;
    int choice = 0;

    public StudentController(Scanner scanner, StudentService studentService) {
        this.scanner = scanner;
        this.studentService = studentService;
    }

    public void createAccount() {
        studentService.createAccount();
        logInAccount();
    }

    /**
     * This method details how the student will interact with the login page.
     * Student can create account if needed.
     */
    public void logInAccount() {
        System.out.println("Welcome to the Student page!");
        System.out.println("Please enter your credentials.");

        System.out.println();

        System.out.println("Do you not have an account (enter y (Yes) or n (No)): ");
        String character = scanner.next();

        if (character.toLowerCase() == "y") {
            createAccount();
        }

        System.out.println();

        System.out.println("Username: ");
        String username = scanner.next();

        System.out.println();

        System.out.println("Password: ");
        String password = scanner.next();

        // TODO: modify once database is complete
        studentService.logInAccount(); // will modify once database is done
        studentHomepage(username);
    }

    public void viewCourses() {
        studentService.viewCourses();
    }

    public void registerForCourseById() {
        studentService.registerForCourseById();
    }

    public void cancelCourseRegistrationById() {
        studentService.cancelCourseRegistrationById();
    }

    public void viewRegisteredCoursesById() {
        studentService.viewRegisteredCoursesById();
    }

    /**
     * This method shows all possible choices a student can make along with what we can expect for input.
     * @param username
     */
    public void studentHomepage(String username) {
        System.out.println("Welcome " + username);

        System.out.println();

        do {
            System.out.println("What would you like to do today?");
            System.out.println("1. View Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Cancel a Course Registration");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Log Out");

            System.out.println();

            System.out.print("Enter your numerical choice from above: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid Input. Please enter a number between 1-3.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCourses();
                case 2:
                    registerForCourseById();
                case 3:
                    cancelCourseRegistrationById();
                case 4:
                    viewRegisteredCoursesById();
                case 5:
                    break;
                default:
                    System.out.println("Invalid Input, Please enter a number from 1-5.");
            }
        } while (choice != 5);
    }
}