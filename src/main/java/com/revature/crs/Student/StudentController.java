package com.revature.crs.Student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.DataNotFoundException;
import com.revature.crs.Exceptions.InvalidInputException;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final StudentService studentService;
    int choice = 0;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void registerStudentPaths(Javalin app) {
        app.post("/students/login", this::getLogInAccount);
        app.post("/students/register", this::postCreateAccount);
        app.get("/courses", this::viewCourses);
    }

    public void postCreateAccount(Context context) throws JsonProcessingException, InvalidInputException {
        ObjectMapper map = new ObjectMapper();
        Student student = map.readValue(context.body(), Student.class);
        Student addedStudent = studentService.createAccount(student);

        if (addedStudent != null) {
            context.status(HttpStatus.ACCEPTED).json(addedStudent);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Creation has failed, please ensure your values are entered properly.");
        }
    }

    /**
     * This method details how the student will interact with the login page.
     * Student can create account if needed.
     */
    public void getLogInAccount(Context context) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Student student = map.readValue(context.body(), Student.class);
        Student loggedInStudent = studentService.logInAccount(student); // will modify once database is done

        if (loggedInStudent != null) {
            context.status(HttpStatus.ACCEPTED).json(loggedInStudent);
        }

        else {
            context.status(HttpStatus.UNAUTHORIZED).result("You do not have access to this account.");
        }
    }

    public void viewCourses(Context context) throws DataNotFoundException {
        List<Course> courses = studentService.viewCourses();
        if (courses != null) {
            context.status(HttpStatus.ACCEPTED).json(courses);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("There are no courses available.");
        }
    }

    public void registerForCourseById(Context context) {
        studentService.registerForCourseById();
    }

    public void cancelCourseRegistrationById(Context context) {
        studentService.cancelCourseRegistrationById();
    }

    public void viewRegisteredCourses(Context context) {
        studentService.viewRegisteredCourses();
    }
}