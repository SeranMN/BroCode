package com.example.chapter7app;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Teacher implements Serializable {

    @Exclude
    String key;
    String name;
    String address;
    String mob;
    String email;
    String nic;
    String degree;
    String description;
    String subject;

    public Teacher(){

    }
    public Teacher(String address, String degree, String email, String mob, String name, String tnic, String spin,String description) {
        this.name = name;
        this.address = address;
        this.mob = mob;
        this.email = email;
        this.degree = degree;
        this.nic = tnic;
        this.subject = spin;
        this.description = description;

    }



    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getSubject() {
        return subject;
    }

    public String getAddress() {
        return address;
    }

    public String getMob() {
        return mob;
    }

    public String getEmail() {
        return email;
    }

    public String getDegree() {
        return degree;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
