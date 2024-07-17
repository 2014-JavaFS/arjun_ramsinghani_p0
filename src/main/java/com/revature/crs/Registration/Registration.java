package com.revature.crs.Registration;

/**
 * This class models a Registration and its properties.
 */
public class Registration {
    private int registration_id;
    private int course_id;
    private int student_id;

    /** Constructors: Initializes(fills in the information for the class attribute or variables) or Instantiates(creation of the object) a class,
     *  What if don't define constructors? There is an implicit/default constructor that sets everything to null, NoArgs Constructor
     *  What happens to our default/NoArgs constructor if we create a constructor?
     *  OOP - Polymorphism - Method Overloading, changing the number of parameters defined & the actions that take place
     */
    public Registration() {
    }

    /**
     * Reason behind naming the parameters in our constructor the same as our class attributes is to shadow the names,
     * so you know EXACTLY what's being initiliazed
     */
    public Registration(int registration_id, int course_id, int student_id) {
        this.registration_id = registration_id;
        this.course_id = course_id;
        this.student_id = student_id;
    }

    /**
     * Methods - functionality applied to every class
     * Getters & Setters >> Overrides >> Custom Methods
     */
    public int getRegistration_id() {
        return registration_id;
    }

    public void setRegistration_id(int registration_id) {
        this.registration_id = registration_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registration_id=" + registration_id +
                ", course_id=" + course_id +
                ", student_id=" + student_id +
                '}';
    }
}
