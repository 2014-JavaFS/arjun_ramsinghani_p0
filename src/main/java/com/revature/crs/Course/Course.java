package com.revature.crs.Course;

public class Course {
    private int courseId;
    private String courseInitials;
    private int courseNumber;
    private String courseName;
    private String courseDetails;
    private short spotsAvailable;
    private short spotsTotal;
    private String instructorLastName;

    public Course() {
    }

    public Course(String courseInitials, int courseNumber, String courseName) {
        this.courseInitials = courseInitials;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
    }

    public Course(String courseInitials, int courseNumber, String courseName, String courseDetails, short spotsAvailable, short spotsTotal, String instructorLastName) {
        this.courseInitials = courseInitials;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseDetails = courseDetails;
        this.spotsAvailable = spotsAvailable;
        this.spotsTotal = spotsTotal;
        this.instructorLastName = instructorLastName;
    }

    public Course(int courseId, String courseInitials, int courseNumber, String courseName, String courseDetails, short spotsAvailable, short spotsTotal, String instructorLastName) {
        this.courseId = courseId;
        this.courseInitials = courseInitials;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseDetails = courseDetails;
        this.spotsAvailable = spotsAvailable;
        this.spotsTotal = spotsTotal;
        this.instructorLastName = instructorLastName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseInitials() {
        return courseInitials;
    }

    public void setCourseInitials(String courseInitials) {
        this.courseInitials = courseInitials;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    public Short getSpotsAvailable() {
        return spotsAvailable;
    }

    public void setSpotsAvailable(short spotsAvailable) {
        this.spotsAvailable = spotsAvailable;
    }

    public Short getSpotsTotal() {
        return spotsTotal;
    }

    public void setSpotsTaken(short spotsTotal) {
        this.spotsTotal = spotsTotal;
    }

    public String getInstructorLastName() {
        return instructorLastName;
    }

    public void setInstructorLastName(String instructor) {
        this.instructorLastName = instructorLastName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseInitials='" + courseInitials + '\'' +
                ", courseNumber=" + courseNumber +
                ", courseName='" + courseName + '\'' +
                ", spotsAvailable=" + spotsAvailable +
                ", spotsTotal=" + spotsTotal +
                ", instructorLastName='" + instructorLastName + '\'' +
                '}';
    }
}
