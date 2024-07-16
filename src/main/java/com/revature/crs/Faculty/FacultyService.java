package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;
import javax.security.sasl.AuthenticationException;

/** SERVICE CLASS DOCUMENTATION
 * The Service class is used to define the business logic coming to the backend by use of the Javalin and REST API to the Data Access Object (DAO).
 * Instructions on how each method should interact are within each method call.
 */
public class FacultyService {
    private FacultyDAO facultyDAO;

    /**
     * This is a constructor, any dependent objects are provided at initialization.
     * @param facultyDAO - will inherit Faculty DAO and its methods so that we can take actions.
     */
    public FacultyService() {
        facultyDAO = new FacultyDAO();
    }

    /**
     * This method will return the account from the database.
     * @param username - takes in the username provided for login.
     * @param password - takes in the password provided for login.
     * @return faculty upon a successful login.
     * @throws AuthenticationException - if the authentication fails.
     */
    public Faculty logInAccount(String username, String password) throws AuthenticationException {
        Faculty faculty = facultyDAO.logInAccount(username, password);

        if (faculty == null) {
            throw new AuthenticationException("Invalid input. Please try again.");
        }

        return faculty;
    }

    /**
     * This method will create a course.
     * @param course - takes in the course from the json context from our controller.
     * @return a successfully created course.
     */
    public Course createCourse(Course course) {
        if ((course.getCourseInitials() == "") || (course.getCourseName() == "") || (course.getCourseDetails() == "") || (course.getInstructorLastName() == "") || (course.getSpotsTotal() == 0)) {
            return null;
        }

        return facultyDAO.createCourse(course);
    }

    /**
     * This method will update a course.
     * @param course_id - this parameter is used to grab the course.
     * @param course - this parameter is used to update the course.
     * @return the updated course.
     */
    public boolean updateCourseById(int course_id, Course course) {
        // return the updated course
        // here we can actually write the update course method to actually return a course
        // can do by id
        Course existingCourse = facultyDAO.getCourseId(course_id);

        if (existingCourse == null) {
            return false;
        }

        return facultyDAO.updateCourseById(course_id, course);
    }

    /**
     * This method will delete a course.
     * @param courseId - this parameter is how we will locate the course to be deleted so that we make no mistakes in our database.
     * @return the deleted course.
     */
    public boolean deleteCourseById(int courseId) {
        // return the deleted course
        // make sure that the course exists first
        // can do by id
        Course deletedCourse = facultyDAO.getCourseId(courseId);

        if (deletedCourse == null) {
            return false;
        }

        return facultyDAO.deleteCourseById(courseId);
    }

    /**
     * This method will return our course by its id.
     * @param courseId - this parameter is how we will find our course.
     * @return a course
     */
    public Course getCourseById(int courseId) {
        return facultyDAO.getCourseId(courseId);
    }
}