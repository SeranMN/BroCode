package com.example.chapter7app;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String email;
    private String mob;
    private String add;
    private int year;
    private String password;

    public  Student (){}

    public Student(String name, String email, String mob, String add, int year) {
        this.name = name;
        this.email = email;
        this.mob = mob;
        this.add = add;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
