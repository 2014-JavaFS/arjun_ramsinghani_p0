package com.revature.crs.Student;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.DataNotFoundException;
import com.revature.crs.Registration.Registration;
import com.revature.crs.Util.ConnectionUtility;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public Student createAccount(Student student) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "insert into student (username, password, f_name, l_name) values (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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
                        resultSet.getShort("spotsAvailable"),
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

    public Registration registerCourseById(Registration registration) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "insert into course_student (course_id, student_id) values (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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






        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        //return null;
        return registration;
    }

    public void cancelCourseRegistrationById(int student_id) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "delete from course_student where student_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setInt(1, student_id);

            preparedStatement.executeUpdate();
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Registration> viewRegisteredCourses(int student_id) {
        List<Registration> registrations = new ArrayList<>();
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
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
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return registrations;
    }
}