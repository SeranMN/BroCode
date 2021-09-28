package com.example.chapter7app;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Marks implements Serializable {

    @Exclude
    private  String key;
    private String clsID;
    private String testno;
    private String studentID;
    private String mark;


    public Marks(){}

    public Marks(String clsID,String testno,String studentID, String mark) {
        this.studentID = studentID;
        this.testno = testno;
        this.clsID=clsID;
        this.mark=mark;

    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getClsID() {
        return clsID;
    }

    public void setClsID(String clsID) {
        this.clsID = clsID;
    }

    public String getTestno() {
        return testno;
    }

    public void setTestno(String testno) {
        this.testno = testno;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
