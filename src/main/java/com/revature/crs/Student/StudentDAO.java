package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.DataNotFoundException;
import com.revature.crs.Registration.Registration;
import com.revature.crs.Util.ConnectionUtility;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a database.
 * The methods here are mostly filled out, you will just need to add a SQL statement.
 */
public class StudentDAO {
    /**
     * This method will create a student's account.
     * @param student - a student's account.
     * @return a created student account.
     */
    public Student createAccount(Student student) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "insert into student (username, password, f_name, l_name) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Return Generated Keys will return a unique id for the object we are creating.

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getF_name());
            preparedStatement.setString(4, student.getL_name());

            // Executes query
            preparedStatement.executeUpdate();

            // Result Set logic
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int generatedStudentId = resultSet.getInt(1);

                return new Student(
                        generatedStudentId,
                        student.getUsername(),
                        student.getPassword(),
                        student.getF_name(),
                        student.getL_name()
                );
            }
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    /**
     * This method will return the account from the database.
     * @param username - takes in the username provided for login.
     * @param password - takes in the password provided for login.
     * @return faculty upon a successful login.
     */
    public Student logInAccount(String username, String password) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "select * from student where username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (!resultSet.next()) {
                throw new DataNotFoundException("No member with that info found");
            }

            Student student = new Student();

            student.setStudent_id(resultSet.getInt("student_id"));
            student.setUsername(resultSet.getString("username"));
            student.setPassword(resultSet.getString("password"));
            student.setF_name(resultSet.getString("f_name"));
            student.setL_name(resultSet.getString("l_name"));

            return student;
        }

        catch (SQLException | DataNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method gives the student a view of all classes eligible to take.
     * @return a list of courses.
     */
    public List<Course> viewCourses() {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            List<Course> courses = new ArrayList<>();

            String sql = "select * from course;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Course classes = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getString("courseInitials"),
                        resultSet.getInt("courseNumber"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseDetails"),
                        resultSet.getShort("spotsTaken"),
                        resultSet.getShort("spotsTotal"),
                        resultSet.getString("instructor")
                );
                courses.add(classes);
            }
            return courses;
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method will register a student for a given class.
     * This will also check to insure the student is able to register.
     * @param registration - a registration object created.
     * @return a new student registration.
     */
    public Registration registerCourseById(Registration registration) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "insert into course_student (course_id, student_id) values (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Return Generated Keys will return a unique id for the object we are creating.

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setInt(1, registration.getCourse_id());
            preparedStatement.setInt(2, registration.getStudent_id());

            // Result Set logic
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int generatedKeys = resultSet.getInt(1);

                return new Registration(
                        generatedKeys,
                        registration.getCourse_id(),
                        registration.getStudent_id()
                );
            }
            return registration;
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method will delete a course from the student's registration.
     * @param registration_id - an id used to track the students registration to a course.
     */
    public void cancelCourseRegistrationById(int registration_id) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "delete from course_student where registration_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setInt(1, registration_id);

            preparedStatement.executeUpdate();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method gives the student a view of all classes they are registered for.
     * @param student_id - used to find the courses a student is registered in.
     * @return a list of registered courses.
     */
    public List<Registration> viewRegisteredCourses(int student_id) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            List<Registration> registrations = new ArrayList<>();
            String sql = "select * from course_student where student_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student_id);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Registration classes = new Registration(
                        resultSet.getInt("registration_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("student_id")
                );
                registrations.add(classes);
            }
            return registrations;
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method is used to locate a particular class registration.
     * @param registration_id - used to locate a registration.
     * @return a registration
     */
    public Registration getRegistrationId(int registration_id) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "select * from course_student where registration_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Prepared Statement will grab our input
            preparedStatement.setInt(1, registration_id);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Registration registration = new Registration(
                        resultSet.getInt("registration_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("student_id")
                );
                return registration;
            }
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method will update a course by changing the number of spots taken by +1.
     * @param courseId - this parameter is used to grab the course.
     * @param spotsTaken - this parameter is used to update the number of spots taken in a course.
     * @return the updated course.
     */
    public boolean updateCourseById(int courseId, short spotsTaken) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "update course set spotsTaken = ? where course_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setShort(1, ++spotsTaken); // pre increment will increment the value before any logic (in this case setting the value) is done to it
            preparedStatement.setInt(2, courseId);

            preparedStatement.executeUpdate();

            return true;
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method will return our course by its id.
     * @param courseId - this parameter is how we will find our course.
     * @return a course
     */
    public Course getCourseId(int courseId) throws Exception {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "select * from course where course_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Prepared Statement will grab our input
            preparedStatement.setInt(1, courseId);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Course course = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getString("courseInitials"),
                        resultSet.getInt("courseNumber"),
                        resultSet.getString("courseName"),
                        resultSet.getString("courseDetails"),
                        resultSet.getShort("spotsTaken"),
                        resultSet.getShort("spotsTotal"),
                        resultSet.getString("instructor")
                );
                return course;
            }

            else {
                throw new Exception("A course with this Id was not found.");
            }

        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}