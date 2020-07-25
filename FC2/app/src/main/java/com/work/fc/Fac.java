package com.work.fc;

public class Fac {
    String faculty_name;
    String subjects;
    String ratings;

    public Fac(String faculty_name, String subjects, String ratings) {
        this.faculty_name = faculty_name;
        this.subjects = subjects;
        this.ratings = ratings;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }
}
