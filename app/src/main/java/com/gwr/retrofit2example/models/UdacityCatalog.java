package com.gwr.retrofit2example.models;

import java.util.List;

/**
 * Created by willi on 31/07/2016.
 */
public class UdacityCatalog {

    public List<Course> courses;


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
