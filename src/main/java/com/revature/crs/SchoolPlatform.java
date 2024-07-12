package com.revature.crs;

import com.revature.crs.Faculty.FacultyController;
import com.revature.crs.Faculty.FacultyService;
import com.revature.crs.Student.StudentController;
import com.revature.crs.Student.StudentService;
import io.javalin.Javalin;

public class SchoolPlatform {

    /**
     * This is the main method.
     * The main method is the entry point to all Java programs.
     * When a program starts, the JVM looks specifically for this method.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Welcome to School Platform!");
        Javalin app = Javalin.create();
        FacultyService facultyService = new FacultyService();
        FacultyController facultyController = new FacultyController(facultyService);
        StudentService studentService = new StudentService();
        StudentController studentController = new StudentController(studentService);
        facultyController.registerFacultyPaths(app);
        studentController.registerStudentPaths(app);
        app.start(8080);
    }
}