package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;
import com.revature.crs.SchoolPlatform;

import java.util.Scanner;

public class FacultyController {
    public Scanner scanner;
    private final FacultyService facultyService;
    int choice = 0;

    public FacultyController(Scanner scanner, FacultyService facultyService) {
        this.scanner = scanner;
        this.facultyService = facultyService;
    }

    public void logInAccount() {
        System.out.println("Welcome to the Faculty page!");
        System.out.println("Please enter your credentials.");

        System.out.println();

        System.out.println("Username: ");
        String username = scanner.next();

        System.out.println();

        System.out.println("Password: ");
        String password = scanner.next();

        // TODO: modify once database is complete
        facultyService.logInAccount(username, password); // will modify once database is done
        facultyHomepage(username);
    }

    // Completed
    public void facultyHomepage(String username) {
        System.out.println("Welcome " + username);

        System.out.println();

        do {
            System.out.println("What would you like to do today?");
            System.out.println("1. Create a Course");
            System.out.println("2. Change Course Details");
            System.out.println("3. Remove a Class");
            System.out.println("4. Log Out");

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
                    createCourse();
                case 2:
                    updateCourse();
                case 3:
                    deleteCourse();
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input, Please enter a number from 1-4.");
            }
        } while (choice != 4);
    }

    public void createCourse() {
        facultyService.createCourse();
    }

    public void updateCourse() {
        facultyService.updateCourse();
    }

    public void deleteCourse() {
        facultyService.deleteCourse();
    }
}
