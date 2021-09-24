package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class AddClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
         EditText edit_TutorName = findViewById(R.id.edtxt01);
         EditText edit_Degree = findViewById(R.id.edtxt04);
         EditText edit_ALYear = findViewById(R.id.edtxtn01);
         EditText edit_Subject = findViewById(R.id.edtxt03);
         EditText edit_Date = findViewById(R.id.edtxtd01);
         EditText edit_Time = findViewById(R.id.edtxtt01);
        Button btn = findViewById(R.id.btn04);
        DAOClassTutor dao = new DAOClassTutor();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassTutor cla = new ClassTutor
                        (
                                edit_TutorName.getText().toString(),
                                edit_Degree.getText().toString(),
                                edit_ALYear.getText().toString(),
                                edit_Subject.getText().toString(),
                                edit_Date.getText().toString(),
                                edit_Time.getText().toString()
                        );
                dao.add(cla).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getApplicationContext(), "Marks Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });


            };

    });

    }
}

