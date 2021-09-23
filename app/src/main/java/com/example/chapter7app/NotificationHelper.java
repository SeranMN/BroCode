package com.example.chapter7app;

import androidx.annotation.NonNull;

public class NotificationHelper {

    String Topic;
    String Message;
    String Date;
    String Time;
    String AnncClass;

    public NotificationHelper(String topic, String message, String date, String time, String aClass) {
        Topic = topic;
        Message = message;
        Date = date;
        Time = time;
        AnncClass = aClass;
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

    public String getAnncClass() {
        return AnncClass;
    }

    public void setAnncClass(String anncClass) {
        AnncClass = anncClass;
    }
}
