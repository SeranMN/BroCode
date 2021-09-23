package com.example.chapter7app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class Add_Notification extends AppCompatActivity {

    EditText Topic, Message, Date, Time;
    Spinner Class;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbRef;
    Button btnAdd;

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

        List<String> Classes = Arrays.asList("2021 A/L", "2022 A/L", "2023 A/L");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,Classes);
        Class.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                dbRef = firebaseDatabase.getReference("Notification");

                String tTopic = Topic.getText().toString();
                String tMessage = Message.getText().toString();
                String tDate = Date.getText().toString();
                String tTime = Time.getText().toString();
                String tClass = Class.getSelectedItem().toString();

                NotificationHelper notificationHelper = new NotificationHelper(tTopic,tMessage,tDate,tTime,tClass);

                dbRef.child(tTopic).setValue(notificationHelper);
                finish();
            }
        });
    }
}