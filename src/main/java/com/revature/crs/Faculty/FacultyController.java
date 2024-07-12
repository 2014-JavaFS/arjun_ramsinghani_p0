package com.revature.crs.Faculty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.crs.Course.Course;
import com.revature.crs.Student.Student;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public void registerFacultyPaths(Javalin app) {
        app.post("/faculties/login", this::getLogInAccount);
        app.post("/courses", this::postCreateCourse);
        app.put("/courses/{courseId}", this::putUpdateCourseById);
        app.delete("/courses/{courseId}", this::deleteCourseById);

    }

    /**
     * This method details how the faculty will interact with the login page.
     */
    public void getLogInAccount(Context context) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Faculty faculty = map.readValue(context.body(), Faculty.class);
        Faculty loggedInFaculty = facultyService.logInAccount(faculty); // will modify once database is done

        if (loggedInFaculty != null) {
            context.status(HttpStatus.ACCEPTED).json(loggedInFaculty);
        }

        else {
            context.status(HttpStatus.UNAUTHORIZED).result("You do not have access to this account.");
        }
    }

    public void postCreateCourse(Context context) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Course course = map.readValue(context.body(), Course.class);
        Course addedCourse = facultyService.createCourse(course);

        if (addedCourse != null) {
            context.status(HttpStatus.ACCEPTED).json(addedCourse);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("The course has not been added, please try again.");
        }
    }

    public void putUpdateCourseById(Context context) throws JsonProcessingException {
        ObjectMapper map = new ObjectMapper();
        Course course = map.readValue(context.body(), Course.class);
        int courseId = Integer.parseInt(context.pathParam("courseId"));
        Course updatedCourse = facultyService.updateCourseById(courseId, course);

        if (updatedCourse != null) {
            context.status(HttpStatus.ACCEPTED).json(updatedCourse);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("The course could not be updated, please try again");
        }
    }

    public void deleteCourseById(Context context) {
        int courseId = Integer.parseInt(context.pathParam("courseId"));
        Course deleteCourse = facultyService.deleteCourseById(courseId);

        if (deleteCourse != null) {
            context.status(HttpStatus.ACCEPTED).json(deleteCourse);
        }

        else {
            context.status(HttpStatus.BAD_REQUEST).result("Deletion was not successful, please try again.");
        }
    }
}