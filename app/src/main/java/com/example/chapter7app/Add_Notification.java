package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Add_Notification extends AppCompatActivity {

    EditText Topic, Message, Date, Time;
    Spinner Class;
    Button btnAdd;

    int hour, min;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        Topic = findViewById(R.id.et_Topic);
        Message = findViewById(R.id.et_Message);
        Date = findViewById(R.id.et_Date);
        Time = findViewById(R.id.et_Time);
        Class = findViewById(R.id.spinner_Class);
        btnAdd = findViewById(R.id.btn_Add_Announcement);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentHour = calendar.get(Calendar.HOUR);
        final int currentMinute = calendar.get(Calendar.MINUTE);
        final int currentSession = calendar.get(Calendar.AM_PM);

        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add_Notification.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month +  1;
                        String date = year+"-"+month+"-"+day;
                        Date.setText(date);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Add_Notification.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour = hourOfDay;
                        min = minute;

                        String time = hour+" : "+min;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH : mm");

                        try{
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh : mm aa");
                            Time.setText(f12Hours.format(date));

                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                },currentHour,currentMinute,false);
                timePickerDialog.updateTime(hour,min);
                timePickerDialog.show();
            }
        });

        List<String> Classes = Arrays.asList("2021 A/L", "2022 A/L", "2023 A/L");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,Classes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        Class.setAdapter(adapter);
        NotificationDAO notificationDAO = new NotificationDAO();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tTopic = Topic.getText().toString();
                String tMessage = Message.getText().toString();
                String tDate = Date.getText().toString();
                String tTime = Time.getText().toString();
                String tClass = Class.getSelectedItem().toString();

               Notification notification = new Notification(tTopic,tMessage,tDate,tTime,tClass);

               notificationDAO.add(notification).addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void unused) {
                       Toast.makeText(getApplicationContext(), "Notification Saved", Toast.LENGTH_SHORT).show();
                   }
               }
               ).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });
    }
}