package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.DataNotFoundException;
import com.revature.crs.Exceptions.InvalidInputException;
import java.util.List;

/** SERVICE CLASS DOCUMENTATION
 * The Service class is used to define the business logic coming to the backend by use of the Javalin and REST API to the Data Access Object (DAO).
 * Instructions on how each method should interact are within each method call.
 */
public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    public Student logInAccount(Student student) {
        // return the account from the database
        return studentDAO.logInAccount(student);
    }

    public Student createAccount(Student student) throws InvalidInputException {
        // return the created account
        if ((student.getUsername() == "") || (student.getPassword() == "") || (student.getF_name() == "") || (student.getL_name() == "")) {
            throw new InvalidInputException("One or more fields are null");
        }

        return studentDAO.createAccount(student);
    }

    public List<Course> viewCourses() throws DataNotFoundException {
        // return a list of all courses
        List<Course> courses = studentDAO.viewCourses();

        if (courses.isEmpty()) {
            throw new DataNotFoundException("No courses available");
        }

        else {
            return courses;
        }
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
        studentDAO.viewRegisteredCourses();
    }

    // TODO: modify once database is complete
    public void viewRegisteredCourses() {
        // return a list of all courses the student registered for
    }
}