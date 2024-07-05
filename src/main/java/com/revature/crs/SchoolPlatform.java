package com.revature.crs;

import java.util.Scanner;

public class SchoolPlatform {
    public static void main(String[] args) {
        System.out.println("Welcome to School Platform!");

        int choice = 0;
        Scanner scanner = new Scanner(System.in);

        do {
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

            switch (choice) {
                case 1:
                    System.out.println("Welcome to Faculty login. Please present your credentials.");
                    break;
                case 2:
                    System.out.println("Welcome to Student login. Please present your credentials.");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Input. Please enter a number between 1-3.");
            }
        }

        while (choice != 3);
    }
}
