package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;

/** SERVICE CLASS DOCUMENTATION
 * The Service class is used to define the business logic coming to the backend by use of the Javalin and REST API to the Data Access Object (DAO).
 * Instructions on how each method should interact are within each method call.
 */
public class FacultyService {
    private FacultyDAO facultyDAO;

    public FacultyService() {
        facultyDAO = new FacultyDAO();
    }

    public Faculty logInAccount(Faculty faculty) {
        // return the account from the database
        return facultyDAO.logInAccount(faculty);
    }

    public Course createCourse(Course course) {
        // return the created course
        if ((course.getCourseInitials() == "") || (course.getCourseName() == "") || (course.getCourseDetails() == "") || (course.getInstructor() == "")) {
            return null;
        }

        return facultyDAO.createCourse(course);
    }

    public Course updateCourseById(int courseId, Course course) {
        // return the updated course
        // here we can actually write the update course method to actually return a course
        // can do by id
        Course existingCourse = facultyDAO.getCourseId(courseId);

        if (existingCourse == null) {
            return null;
        }

        facultyDAO.updateCourseById(courseId, course);
        return facultyDAO.getCourseId(courseId);
    }

    public Course deleteCourseById(int courseId) {
        // return the deleted course
        // make sure that the course exists first
        // can do by id
        Course deletedCourse = facultyDAO.getCourseId(courseId);

        if (deletedCourse == null) {
            return null;
        }

        facultyDAO.deleteCourseById(courseId);
        return deletedCourse;
    }
}