package com.revature.crs.Student;

import com.revature.crs.Course.Course;
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

    /**
     * This is a constructor, any dependent objects are provided at initialization.
     */
    public StudentService() {
        studentDAO = new StudentDAO();
    }

    /**
     * This method will return the account from the database.
     * @param username - takes in the username provided for login.
     * @param password - takes in the password provided for login.
     * @return faculty upon a successful login.
     * @throws AuthenticationException - if the authentication fails.
     */
    public Student logInAccount(String username, String password) throws AuthenticationException {
        Student student = studentDAO.logInAccount(username, password);

        if (student == null) {
            throw new AuthenticationException("Invalid input. Please try again.");
        }

        return student;
    }

    /**
     * This method will create a student's account.
     * @param student - a student's account.
     * @return a created student account.
     * @throws InvalidInputException - if our input is not validated through the validation if process.
     */
    public Student createAccount(Student student) throws InvalidInputException {
        if ((student.getUsername() == "") || (student.getPassword() == "") || (student.getF_name() == "") || (student.getL_name() == "")) {
            throw new InvalidInputException("One or more fields are null");
        }

        return studentDAO.createAccount(student);
    }

    /**
     * This method gives the student a view of all classes eligible to take.
     * @return a list of courses.
     */
    public List<Course> viewCourses() {
        return studentDAO.viewCourses();
    }

    /**
     * This method will register a student for a given class.
     * This will also check to insure the student is able to register.
     * @param registration - a registration object created.
     * @return
     */
    public Registration registerForCourseById(Registration registration) throws Exception {
        int course_id = registration.getCourse_id();
        Course course = studentDAO.getCourseId(course_id);

        if (course.getSpotsTaken() >= course.getSpotsTotal()) {
            throw new Exception("There is no more room for registration.");
        }

        Registration newRegistration = studentDAO.registerCourseById(registration);
        studentDAO.updateCourseById(course_id, course.getSpotsTaken());
        return newRegistration;
    }

    /**
     * This method will delete a course from the student's registration.
     * @param registration_id - an id used to track the students registration to a course.
     * @return the deleted registration
     */
    public Registration cancelCourseRegistrationById(int registration_id) {
        Registration deletedRegistration = studentDAO.getRegistrationId(registration_id);

        if (deletedRegistration == null) {
            return null;
        }

        studentDAO.cancelCourseRegistrationById(registration_id);
        return deletedRegistration;
    }

    /**
     * This method gives the student a view of all classes they are registered for.
     * @param student_id - used to find the courses a student is registered in.
     * @return a list of registered courses.
     */
    public List<Registration> viewRegisteredCourses(int student_id) {
        return studentDAO.viewRegisteredCourses(student_id);
    }
}