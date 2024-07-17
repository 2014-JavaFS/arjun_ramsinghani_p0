package com.revature.crs.Student;

/**
 * This class models a Student and their properties.
 */
public class Student {
    private int student_id;
    private String username;
    private String password;
    private String f_name;
    private String l_name;

    /** Constructors: Initializes(fills in the information for the class attribute or variables) or Instantiates(creation of the object) a class,
     *  What if don't define constructors? There is an implicit/default constructor that sets everything to null, NoArgs Constructor
     *  What happens to our default/NoArgs constructor if we create a constructor?
     *  OOP - Polymorphism - Method Overloading, changing the number of parameters defined & the actions that take place
     */
    public Student() {
    }

    /**
     * Reason behind naming the parameters in our constructor the same as our class attributes is to shadow the names,
     * so you know EXACTLY what's being initiliazed
     */
    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Student(int student_id, String username, String password, String f_name, String l_name) {
        this.student_id = student_id;
        this.username = username;
        this.password = password;
        this.f_name = f_name;
        this.l_name = l_name;
    }

    /**
     * Methods - functionality applied to every class
     * Getters & Setters >> Overrides >> Custom Methods
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", student_id=" + student_id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                '}';
    }
}
