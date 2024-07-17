package com.revature.crs.Faculty;

import com.revature.crs.Course.Course;
import com.revature.crs.Exceptions.DataNotFoundException;
import com.revature.crs.Util.ConnectionUtility;
import java.sql.*;


/**
 * A DAO is a class that mediates the transformation of data between the format of objects in Java to rows in a database.
 * The methods here are mostly filled out, you will just need to add a SQL statement.
 */
public class FacultyDAO {
    /**
     * This method will return the account from the database.
     * @param username - takes in the username provided for login.
     * @param password - takes in the password provided for login.
     * @return faculty upon a successful login.
     */
    public Faculty logInAccount(String username, String password) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "select * from faculty where username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (!resultSet.next()) {
                throw new DataNotFoundException("No member with that info found");
            }

            Faculty faculty = new Faculty();

            faculty.setFaculty_id(resultSet.getInt("faculty_id"));
            faculty.setUsername(resultSet.getString("username"));
            faculty.setPassword(resultSet.getString("password"));
            faculty.setF_name(resultSet.getString("f_name"));
            faculty.setL_name(resultSet.getString("l_name"));

            return faculty;
        }

        catch (SQLException | DataNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method will create a course.
     * @param course - takes in the course from the json context from our controller.
     * @return a successfully created course.
     */
    public Course createCourse(Course course) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "insert into course (courseInitials, courseNumber, courseName, courseDetails, spotsTaken, spotsTotal, instructor) values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setString(1, course.getCourseInitials());
            preparedStatement.setInt(2, course.getCourseNumber());
            preparedStatement.setString(3, course.getCourseName());
            preparedStatement.setString(4, course.getCourseDetails());
            preparedStatement.setShort(5, course.getSpotsTaken());
            preparedStatement.setShort(6, course.getSpotsTotal());
            preparedStatement.setString(7, course.getInstructorLastName());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException("Course was not inserted into the database");
            }

            return course;
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * This method will update a course by changing the instructor.
     * @param courseId - this parameter is used to grab the course.
     * @param course - this parameter is used to update the course.
     * @return the updated course.
     */
    public boolean updateCourseById(int courseId, Course course) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "update course set instructor = ? where course_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setString(1, course.getInstructorLastName());
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
     * This method will delete a course.
     * @param course_id - this parameter is how we will locate the course to be deleted so that we make no mistakes in our database.
     * @return the deleted course.
     */
    public boolean deleteCourseById(int course_id) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "delete from course where course_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set method, the user input starts at index 1 or 0
            preparedStatement.setInt(1, course_id);

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
    public Course getCourseId(int courseId) {
        try (Connection connection = ConnectionUtility.getConnectionUtility().getConnection()) {
            String sql = "select * from course where course_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Prepared Statement will grab our input
            preparedStatement.setInt(1, courseId);

            // Result Set logic
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
        }

        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}