package com.example.chapter7app;

import java.sql.Time;

public class ClassTutor {

    private String tutor;
    private String degree;
    private String alYear;
    private String subject;
    private String date;
    private String time;

    public ClassTutor(){}
    public ClassTutor(String tutor, String degree, String alYear, String subject, String date, String time) {
        this.tutor = tutor;
        this.degree = degree;
        this.alYear = alYear;
        this.subject = subject;
        this.date = date;
        this.time = time;

    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getAlYear() {
        return alYear;
    }

    public void setAlYear(String alYear) {
        this.alYear = alYear;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
