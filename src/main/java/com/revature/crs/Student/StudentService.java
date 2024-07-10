package com.revature.crs.Student;

/** SERVICE CLASS DOCUMENTATION
 * The Service class is used to define the business logic coming to the backend by use of the Javalin and REST API to the Data Access Object (DAO).
 * Instructions on how each method should interact are within each method call.
 */
public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    // TODO: modify once database is complete
    public void logInAccount() {
        // return the account from the database
    }

    // TODO: modify once database is complete
    public void createAccount() {
        // return the created account
    }

    // TODO: modify once database is complete
    public void viewCourses() {
        // return a list of all courses
    }

    // TODO: modify once database is complete
    public void registerForCourseById() {
        // find course by id through view courses
        // return updated registration
    }

    // TODO: modify once database is complete
    public void cancelCourseRegistrationById() {
        // find in registered courses
        // return updated registration
    }

    // TODO: modify once database is complete
    public void viewRegisteredCoursesById() {
        // return a list of all courses the student registered for
    }
}