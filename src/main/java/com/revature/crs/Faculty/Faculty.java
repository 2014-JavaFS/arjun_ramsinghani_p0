package com.revature.crs.Faculty;

/**
 * This class models a Faculty and their properties.
 */
public class Faculty {
    private int faculty_id;
    private String username;
    private String password;
    private String f_name;
    private String l_name;

    /** Constructors: Initializes(fills in the information for the class attribute or variables) or Instantiates(creation of the object) a class,
     *  What if don't define constructors? There is an implicit/default constructor that sets everything to null, NoArgs Constructor
     *  What happens to our default/NoArgs constructor if we create a constructor?
     *  OOP - Polymorphism - Method Overloading, changing the number of parameters defined & the actions that take place
     */
    public Faculty() {
    }

    /**
     * Reason behind naming the parameters in our constructor the same as our class attributes is to shadow the names,
     * so you know EXACTLY what's being initiliazed
     */
    public Faculty(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Faculty(int faculty_id, String username, String password, String f_name, String l_name) {
        this.faculty_id = faculty_id;
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

    public int getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(int faculty_id) {
        this.faculty_id = faculty_id;
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
        return "Faculty{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", faculty_id=" + faculty_id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                '}';
    }
}
