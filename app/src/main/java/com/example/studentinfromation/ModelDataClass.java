package com.example.studentinfromation;



// we make a model data so why we need a model data class that contain the same value of with our database
// NOTE : we use sqlite database system
// so in this model class we can create an instance of it in database source class

import java.io.Serializable;

public class ModelDataClass implements Serializable {
    int id;
    int studentid;
    String name;
    String courses;
    String section;
    String regstatus;

    public ModelDataClass(int id, int studentid, String name, String courses, String section, String regstatus) {
        this.id = id;
        this.studentid = studentid;
        this.name = name;
        this.courses = courses;
        this.section = section;
        this.regstatus = regstatus;
    }

    public ModelDataClass(int studentid, String name, String courses, String section, String regstatus) {
        this.studentid = studentid;
        this.name = name;
        this.courses = courses;
        this.section = section;
        this.regstatus = regstatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getRegstatus() {
        return regstatus;
    }

    public void setRegstatus(String regstatus) {
        this.regstatus = regstatus;
    }
}

