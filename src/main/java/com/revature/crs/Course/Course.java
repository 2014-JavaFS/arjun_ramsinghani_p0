package com.revature.crs.Course;

/**
 * This class models a Course and its properties.
 */
public class Course {
    private int course_id;
    private String courseInitials;
    private int courseNumber;
    private String courseName;
    private String courseDetails;
    private short spotsTaken;
    private short spotsTotal;
    private String instructorLastName;

    /** Constructors: Initializes (fills in the information for the class attribute or variables) or Instantiates (creation of the object) a class,
     *  What if don't define constructors? There is an implicit/default constructor that sets everything to null, NoArgs Constructor
     *  What happens to our default/NoArgs constructor if we create a constructor?
     *  OOP - Polymorphism - Method Overloading, changing the number of parameters defined & the actions that take place
     */
    public Course() {
    }

    /**
     * Reason behind naming the parameters in our constructor the same as our class attributes is to shadow the names,
     * so you know EXACTLY what's being initialized
     */
    public Course(String courseInitials, int courseNumber, String courseName) {
        this.courseInitials = courseInitials;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
    }

    public Course(String courseInitials, int courseNumber, String courseName, String courseDetails, short spotsTaken, short spotsTotal, String instructorLastName) {
        this.courseInitials = courseInitials;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseDetails = courseDetails;
        this.spotsTaken = spotsTaken;
        this.spotsTotal = spotsTotal;
        this.instructorLastName = instructorLastName;
    }

    public Course(int course_id, String courseInitials, int courseNumber, String courseName, String courseDetails, short spotsTaken, short spotsTotal, String instructorLastName) {
        this.course_id = course_id;
        this.courseInitials = courseInitials;
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseDetails = courseDetails;
        this.spotsTaken = spotsTaken;
        this.spotsTotal = spotsTotal;
        this.instructorLastName = instructorLastName;
    }

    /**
     * Methods - functionality applied to every class
     * Getters & Setters >> Overrides >> Custom Methods
     */
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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

    public Short getSpotsTaken() {
        return spotsTaken;
    }

    public void setSpotsTaken(short spotsAvailable) {
        this.spotsTaken = spotsAvailable;
    }

    public Short getSpotsTotal() {
        return spotsTotal;
    }

    public void setSpotsTotal(short spotsTotal) {
        this.spotsTotal = spotsTotal;
    }

    public String getInstructorLastName() {
        return instructorLastName;
    }

    public void setInstructorLastName(String instructorLastName) {
        this.instructorLastName = instructorLastName;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", courseInitials='" + courseInitials + '\'' +
                ", courseNumber=" + courseNumber +
                ", courseName='" + courseName + '\'' +
                ", spotsTaken=" + spotsTaken +
                ", spotsTotal=" + spotsTotal +
                ", instructorLastName='" + instructorLastName + '\'' +
                '}';
    }
}
