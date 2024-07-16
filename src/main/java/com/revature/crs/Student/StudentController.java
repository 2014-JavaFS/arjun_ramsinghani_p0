package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.InvalidInputException;
import com.revature.crs.Registration.Registration;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import javax.security.sasl.AuthenticationException;
import java.util.List;

public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void registerStudentPaths(Javalin app) {
        app.post("/students/login", this::getLogInAccount); //done
        app.post("/students/registerAccount", this::postCreateAccount); //done
        app.get("/students/courses", this::viewCourses); //done
        app.post("/students/registerCourse", this::registerForCourseById); //done
        app.delete("/students/cancelRegisteredCourse/{course_id}", this::cancelCourseRegistrationById); //done
        app.get("/students/{student_id}/coursesRegistered", this::viewRegisteredCourses); //done
    }

    public void postCreateAccount(Context context) throws InvalidInputException {
        Student student = context.bodyAsClass(Student.class);
        context.json(studentService.createAccount(student));

        if (studentService.createAccount(student) == null) {
            context.status(HttpStatus.FORBIDDEN);
        }

        context.status(HttpStatus.CREATED);
    }

    /**
     * This method details how the student will interact with the login page.
     * Student can create account if needed.
     */
    public void getLogInAccount(Context context) {
        String username = context.queryParam("username");
        String password = context.queryParam("password");

        try {
            Student student = studentService.logInAccount(username, password);
            context.header("username", student.getUsername());
            context.header("password", student.getPassword());
            context.status(HttpStatus.ACCEPTED).result("Successful login");
        }

        catch (AuthenticationException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    public void viewCourses(Context context) {
        List<Course> courses = studentService.viewCourses();

        if (courses != null) {
            context.status(HttpStatus.ACCEPTED).json(courses);
        }

        else {
            context.status(HttpStatus.NOT_FOUND).result("There are no courses available.");
        }
    }

    public void registerForCourseById(Context context) {
        // Body as Class takes the body from an http request and maps it to an argument class through a Reflection API
        // allows Jackson to use a no-arg constructor and setters to build a registration object in memory where json fields match exactly to our model attributes and setters
        Registration registration = context.bodyAsClass(Registration.class);
        Registration newRegistration = studentService.registerForCourseById(registration);

        if (newRegistration != null) {
            context.status(HttpStatus.CREATED).json(newRegistration);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Course was not successfully registered");
        }
    }

    public void cancelCourseRegistrationById(Context context) {
        int course_id = Integer.parseInt(context.pathParam("course_id"));
        Registration deletedRegistration = studentService.cancelCourseRegistrationById(course_id);
        context.status(HttpStatus.OK).json(deletedRegistration);
    }

    public void viewRegisteredCourses(Context context) {
        int student_id = Integer.parseInt(context.pathParam("student_id"));
        List<Registration> registrations = studentService.viewRegisteredCourses(student_id);

        if (registrations != null) {
            context.status(HttpStatus.ACCEPTED).json(registrations);
        }

        else {
            context.status(HttpStatus.NOT_FOUND).result("No courses have been registered to your name");
        }
    }
}