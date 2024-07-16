package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.DataNotFoundException;
import com.revature.crs.Exceptions.InvalidInputException;
import com.revature.crs.Registration.Registration;

import javax.security.sasl.AuthenticationException;
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

    public Student logInAccount(String username, String password) throws AuthenticationException {
        // return the account from the database
        Student student = studentDAO.logInAccount(username, password);

        if (student == null) {
            throw new AuthenticationException("Invalid input. Please try again.");
        }

        return student;
    }

    public Student createAccount(Student student) throws InvalidInputException {
        // return the created account
        if ((student.getUsername() == "") || (student.getPassword() == "") || (student.getF_name() == "") || (student.getL_name() == "")) {
            throw new InvalidInputException("One or more fields are null");
        }

        return studentDAO.createAccount(student);
    }

    public List<Course> viewCourses() {
        // return a list of all courses
        return studentDAO.viewCourses();

//        if (courses.isEmpty()) {
//            throw new DataNotFoundException("No courses available");
//        }
//
//        else {
//            return courses;
//        }
    }

    // TODO: modify once database is complete
    public void registerForCourseById(Registration registration) {
        // find course by id through view courses
        // return updated registration
        studentDAO.registerCourseById(registration);
    }

    // TODO: modify once database is complete
    public void cancelCourseRegistrationById(int course_id) {
        // find in registered courses
        // return updated registration
        studentDAO.cancelCourseRegistrationById(course_id);
    }

    // TODO: modify once database is complete
    public List<Registration> viewRegisteredCourses(int student_id) {
        // return a list of all courses the student registered for
        return studentDAO.viewRegisteredCourses(student_id);
    }
}