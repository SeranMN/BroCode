package com.example.chapter7app;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Notification implements Serializable {

    @Exclude
    String Key;
    String Topic;
    String Message;
    String Date;
    String Time;
    String ALYear;

    public  Notification(){

    }

    public Notification(String topic, String message, String date, String time, String alYear) {
        this.Topic = topic;
        this.Message = message;
        this.Date = date;
        this.Time = time;
        this.ALYear = alYear;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getALYear() { return ALYear; }

    public void setALYear(String ALYear) {
        this.ALYear = ALYear;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
