package com.example.chapter7app;

public class addTeacherHelper {
    String name;
    String address;
    String mob;
    String email;
    String nic;
    String subject;
    public addTeacherHelper(String name, String address, String mob, String email, String degree, String tnic, String spin) {
        this.name = name;
        this.address = address;
        this.mob = mob;
        this.email = email;
        this.degree = degree;
        this.nic = tnic;
        this.subject = spin;

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

    String degree;



}
