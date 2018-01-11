package com.gwr.retrofit2example.models;

import java.util.List;

/**
 * Created by willi on 31/07/2016.
 */
public class Course {

    public String title;
    public String subtitle;
    public String image;
    public String homepage;
    public List<Instructor> instructors;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtittle() {
        return subtitle;
    }

    public void setSubtittle(String subtittle) {
        this.subtitle = subtittle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }
}
