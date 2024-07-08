package com.revature.crs;

import com.revature.crs.Faculty.FacultyController;
import com.revature.crs.Faculty.FacultyService;
import com.revature.crs.Student.StudentController;
import com.revature.crs.Student.StudentService;
import java.util.Scanner;

public class SchoolPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FacultyService facultyService = new FacultyService();
        FacultyController facultyController = new FacultyController(scanner, facultyService);
        StudentService studentService = new StudentService();
        StudentController studentController = new StudentController(scanner, studentService);
        System.out.println("Welcome to School Platform!");

        int choice = 0;

        do { // we want to do this only once
            System.out.println("Which account do you want to log in to today?");
            System.out.println("1. Faculty");
            System.out.println("2. Student");
            System.out.println("3. Exit");

            System.out.println();

            System.out.print("Enter your numeric choice from above: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid Input. Please enter a number between 1-3.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();

            switch (choice) { // helps us with decision-making
                case 1:
                    facultyController.logInAccount();
                    System.exit(0);
                case 2:
                    studentController.logInAccount();
                    System.exit(0);
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Input. Please enter a number between 1-3.");
            }
        } while (choice != 3); // we do not want the code to execute when we exit
    }
}
