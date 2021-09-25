package com.example.chapter7app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    studentDAO dao = new studentDAO();
    EditText name;
    EditText address;
    EditText phone;
    EditText email;
    EditText alYear;
    EditText pwd;
    EditText conPwd;
    String stuName;
    String stuAddress;
    String stuPhone;
    String stuEmail;
    int stuAlYear;
    String stuPwd;
    String stuPwd2;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.etStudentName);
        address = findViewById(R.id.etStudentAdd);
        phone = findViewById(R.id.etStudenPhone);
        email = findViewById(R.id.etStudentMail);
        alYear = findViewById(R.id.studentALYear);
        pwd = findViewById(R.id.etStudentPwd);
        conPwd = findViewById(R.id.etStudentPwd2);
        mAuth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.btnADD);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                stuName = name.getText().toString();
                stuAddress = address.getText().toString();
                stuPhone = phone.getText().toString();
                stuEmail = email.getText().toString();
                stuAlYear= Integer.parseInt(alYear.getText().toString());
                stuPwd = pwd.getText().toString();
                stuPwd2 = conPwd.getText().toString();
                if(stuEmail.isEmpty()){
                    email.setError("Enter Email");
                    email.requestFocus();
                }
                if (stuPwd.isEmpty()){
                    email.setError("Enter Password");
                    email.requestFocus();
                    return;
                }
                if(pwd.length()<6){
                    pwd.setError("Invalid password length");
                    pwd.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(stuEmail).matches()){
                    email.setError("Invalid Email");
                    email.requestFocus();
                    return;
                }

                Student student = new Student(stuName,stuEmail,stuPhone,stuAddress,stuAlYear);
                mAuth.createUserWithEmailAndPassword(stuEmail,stuPwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                String user = mAuth.getCurrentUser().getUid().toString();
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        dao.add(student,user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(getApplicationContext(),"Success Full Registered, " +
                                                        "Check your email", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(),Login.class);
                                                startActivity(intent);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(),"Could not registered"
                                                        , Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }else {
                                        Toast.makeText(getApplicationContext(), "Check Your Email Address again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }

                });




            }
        });


    }
}