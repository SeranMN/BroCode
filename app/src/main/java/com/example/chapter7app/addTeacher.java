package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class addTeacher extends AppCompatActivity {
EditText name,address,mobileNo,email,degree,nic;
Spinner subject;
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        name = findViewById(R.id.et_name);
        nic = findViewById(R.id.et_nic);
        address = findViewById(R.id.et_address);
        mobileNo = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_mail);
        degree = findViewById(R.id.et_degree);
        subject = findViewById(R.id.spinner_subject);
    btn = findViewById(R.id.btn_submit);

        List <String> subjects = Arrays.asList("Aplied Maths","Pure Maths","Chemestry","Physics","ICT","BIO");
        ArrayAdapter adapter = new ArrayAdapter (getApplicationContext(), android.R.layout.simple_spinner_item, subjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        subject.setAdapter(adapter);
        TeacherDAO teacherDAO = new TeacherDAO();

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String tName =  name.getText().toString();
            String tAddress = address.getText().toString();
            String tmob = mobileNo.getText().toString();
            String tEmail =email.getText().toString();
            String tdegree =   degree.getText().toString();

            String tnic = nic.getText().toString();
            String spin = subject.getSelectedItem().toString();


            Teacher teacher = new Teacher( tAddress,  tdegree,tEmail, tmob,  tName,  tnic,  spin);


            teacherDAO.add(teacher).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(getApplicationContext(), "Teacher Added", Toast.LENGTH_SHORT).show();
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