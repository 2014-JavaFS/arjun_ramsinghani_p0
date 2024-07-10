package com.revature.crs.Faculty;

/** SERVICE CLASS DOCUMENTATION
 * The Service class is used to define the business logic coming to the backend by use of the Javalin and REST API to the Data Access Object (DAO).
 * Instructions on how each method should interact are within each method call.
 */
public class FacultyService {
    private FacultyDAO facultyDAO;

    public FacultyService() {
        facultyDAO = new FacultyDAO();
    }

    // TODO: modify once database is complete
    public void logInAccount() {
        // return the account from the database
    }

    // TODO: modify once database is complete
    public void createCourse() {
        // return the created course
    }

    // TODO: modify once database is complete
    public void updateCourseById() {
        // return the updated course
        // here we can actually write the update course method to actually return a course
        // can do by id
    }

    // TODO: modify once database is complete
    public void deleteCourseById() {
        // return the deleted course
        // make sure that the course exists first
        // can do by id
    }
}