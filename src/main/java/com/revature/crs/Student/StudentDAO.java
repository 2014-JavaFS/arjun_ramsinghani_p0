package com.revature.crs.Student;

import com.revature.crs.Course.Course;
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

    public Student logInAccount(Student student) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "select * from student where username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setString(1, student.getUsername());
            preparedStatement.setString(2, student.getPassword());

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student students = new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name")
                );
                return students;
            }
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
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
                        resultSet.getInt("courseId"),
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

    public void registerCourseById(int course_id) {}

    public void cancelCourseRegistrationById(int course_id) {}

    public void viewRegisteredCourses() {}
}