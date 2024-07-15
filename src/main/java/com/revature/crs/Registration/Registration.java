package com.revature.crs.Registration;

public class Registration {
    private int registration_id;
    private int course_id;
    private int student_id;

    public Registration() {
    }

    public Registration(int registration_id, int course_id, int student_id) {
        this.registration_id = registration_id;
        this.course_id = course_id;
        this.student_id = student_id;
    }

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
