package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.InvalidInputException;
import com.revature.crs.Registration.Registration;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import javax.security.sasl.AuthenticationException;
import java.util.List;

/**
 * CONTROLLER CLASS DOCUMENTATION
 */
public class StudentController {
    private final StudentService studentService;

    /**
     * This is a constructor, any dependent objects are provided at initialization.
     * @param studentService - will inherit Student Service and its methods so that we can take actions.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * This method will allow us to communicate with our postman and eventually front end for web requests.
     * @param app - communicates with our Javalin application.
     */
    public void registerStudentPaths(Javalin app) {
        app.post("/students/login", this::getLogInAccount); //done
        app.post("/students/registerAccount", this::postCreateAccount); //done
        app.get("/students/courses", this::viewCourses); //done
        app.post("/students/registerCourse", this::registerForCourseById); //done
        app.delete("/students/cancelRegisteredCourse/{course_id}", this::cancelCourseRegistrationById); //done
        app.get("/students/{student_id}/coursesRegistered", this::viewRegisteredCourses); //done
    }

    /**
     * This method will allow the faculty to create a class.
     * Body as Class takes the body from a http request and maps it to an argument class through a Reflection API.
     * Allows Jackson to use a no-arg constructor and setters to build a registration object in memory where json fields match exactly to our model attributes and setters.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void postCreateAccount(Context context) throws InvalidInputException {
        Student student = context.bodyAsClass(Student.class);
        context.json(studentService.createAccount(student));

        if (studentService.createAccount(student) == null) {
            context.status(HttpStatus.FORBIDDEN);
        }

        context.status(HttpStatus.CREATED);
    }

    /**
     * This method will allow the student to login.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
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

    /**
     * This method gives the student a view of all classes eligible to take.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void viewCourses(Context context) {
        List<Course> courses = studentService.viewCourses();

        if (courses != null) {
            context.status(HttpStatus.ACCEPTED).json(courses);
        }

        else {
            context.status(HttpStatus.NOT_FOUND).result("There are no courses available.");
        }
    }

    /**
     * This method will register a student to a course.
     * Body as Class takes the body from a http request and maps it to an argument class through a Reflection API.
     * Allows Jackson to use a no-arg constructor and setters to build a registration object in memory where json fields match exactly to our model attributes and setters.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void registerForCourseById(Context context) throws Exception {
        Registration registration = context.bodyAsClass(Registration.class);

        Registration newRegistration = studentService.registerForCourseById(registration);


        if (newRegistration != null) {
            context.status(HttpStatus.CREATED).json(newRegistration);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Course was not successfully registered");
        }
    }

    /**
     * This method will delete a course from the student's registration.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void cancelCourseRegistrationById(Context context) {
        int course_id = Integer.parseInt(context.pathParam("course_id"));
        Registration deletedRegistration = studentService.cancelCourseRegistrationById(course_id);
        context.status(HttpStatus.OK).json(deletedRegistration);
    }

    /**
     * This method gives the student a view of all classes they are registered for.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
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