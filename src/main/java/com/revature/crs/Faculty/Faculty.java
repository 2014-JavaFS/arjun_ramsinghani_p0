package com.revature.crs.Faculty;

public class Faculty {
    private String username;
    private String password;
    private int faculty_id;
    private String f_name;
    private String l_name;

    public Faculty() {
    }

    public Faculty(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Faculty(String username, String password, int faculty_id, String f_name, String l_name) {
        this.username = username;
        this.password = password;
        this.faculty_id = faculty_id;
        this.f_name = f_name;
        this.l_name = l_name;
    }

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
}
