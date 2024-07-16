package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import javax.security.sasl.AuthenticationException;

public class FacultyController {
    private final FacultyService facultyService;

    /**
     * This is a constructor, any dependent objects are provided at initialization.
     * @param facultyService - will inherit Faculty Service and its methods so that we can take actions.
     */
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    /**
     * This method will allow us to communicate with our postman and eventually front end for web requests.
     * @param app - communicates with our Javalin application.
     */
    public void registerFacultyPaths(Javalin app) {
        app.post("/faculties/login", this::getLogInAccount); //done
        app.post("/faculties/courses", this::postCreateCourse); //done
        app.put("/faculties/coursesUpdate/{course_id}", this::putUpdateCourseById); //done
        app.delete("/faculties/coursesDelete/{course_id}", this::deleteCourseById); //done
    }

    /**
     * This method will allow the faculty to login.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void getLogInAccount(Context context) {
        String username = context.queryParam("username");
        String password = context.queryParam("password");

        try {
            Faculty faculty = facultyService.logInAccount(username, password);
            context.header("username", faculty.getUsername());
            context.header("password", faculty.getPassword());
            context.status(HttpStatus.ACCEPTED).result("Successful login");
        }

        catch (AuthenticationException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * This method will allow the faculty to create a class.
     * Body as Class takes the body from a http request and maps it to an argument class through a Reflection API.
     * Allows Jackson to use a no-arg constructor and setters to build a registration object in memory where json fields match exactly to our model attributes and setters.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void postCreateCourse(Context context) {
        Course course = context.bodyAsClass(Course.class);
        Course addCourse = facultyService.createCourse(course);

        if (addCourse != null) {
            context.status(HttpStatus.CREATED).json(addCourse);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Course creation failed");
        }
    }

    /**
     * This method will allow the faculty to update their class.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void putUpdateCourseById(Context context) {
        int updatedCourseId = Integer.parseInt(context.pathParam("course_id"));
        Course updateCourse = context.bodyAsClass(Course.class);
        boolean checkUpdate = facultyService.updateCourseById(updatedCourseId, updateCourse);

        if (checkUpdate == true) {
            Course foundCourse = facultyService.getCourseById(updatedCourseId);
            context.status(HttpStatus.OK).json(foundCourse);
        }

        else {
            context.status(HttpStatus.NOT_FOUND).result("Course was not successfully updated");
        }
    }

    /**
     * This method will allow the faculty to delete their class.
     * @param context - provides the context either in form of json. Eventually we can also use this with the front end.
     */
    public void deleteCourseById(Context context) {
        int courseId = Integer.parseInt(context.pathParam("course_id")); // parses data through the specified
        boolean deleteCourse = facultyService.deleteCourseById(courseId);

        if (deleteCourse == true) {
            context.status(HttpStatus.ACCEPTED).result("Course successfully deleted");
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Deletion was not successful, please try again.");
        }
    }
}