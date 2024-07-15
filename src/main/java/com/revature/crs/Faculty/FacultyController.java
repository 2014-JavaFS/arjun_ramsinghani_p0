package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import javax.security.sasl.AuthenticationException;

public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public void registerFacultyPaths(Javalin app) {
        app.post("/faculties/login", this::getLogInAccount);
        app.post("/faculties/courses", this::postCreateCourse);
        app.put("/faculties/courses/{courseId}", this::putUpdateCourseById);
        app.delete("/faculties/courses/{courseId}", this::deleteCourseById);

    }

    /**
     * This method details how the faculty will interact with the login page.
     */
    public void getLogInAccount(Context context) {
        String username = context.queryParam("username");
        String password = context.queryParam("password");

        try {
            Faculty faculty = facultyService.logInAccount(username, password);
            context.header("username", faculty.getUsername());
            context.header("password", faculty.getPassword());
            context.status(HttpStatus.ACCEPTED);
        }

        catch (AuthenticationException e) {
            context.status(HttpStatus.UNAUTHORIZED);
        }
    }

    public void postCreateCourse(Context context) {
        Course course = context.bodyAsClass(Course.class);
        context.json(facultyService.createCourse(course));
    }

    public void putUpdateCourseById(Context context) {
        int updatedCourse = Integer.parseInt(context.pathParam("course_id"));
        Course foundCourse = facultyService.getCourseById(updatedCourse);
        context.json(foundCourse);
    }

    public void deleteCourseById(Context context) {
        int courseId = Integer.parseInt(context.pathParam("course_id")); // parses data through the specified
        Course deleteCourse = facultyService.deleteCourseById(courseId);

        if (deleteCourse != null) {
            context.status(HttpStatus.ACCEPTED).json(deleteCourse);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Deletion was not successful, please try again.");
        }
    }
}