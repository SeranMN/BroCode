package com.example.chapter7app;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.sql.Time;

public class ClassTutor implements Serializable {

    @Exclude
     String clsid;
     String tutor;
     String degree;
     String alYear;
     String subject;
     String date;
     String time;
     String key;

    public ClassTutor(){}
    public ClassTutor(String clsid,String tutor, String degree, String alYear, String subject, String date, String time) {
        this.clsid=clsid;
        this.tutor = tutor;
        this.degree = degree;
        this.alYear = alYear;
        this.subject = subject;
        this.date = date;
        this.time = time;

    }
    public String getClsid() {
        return clsid;
    }

    public void setClsid(String clsid) {
        this.clsid = clsid;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
