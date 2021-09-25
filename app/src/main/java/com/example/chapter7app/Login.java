package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText userName;
    EditText password;
    FirebaseAuth mAuth;
    Intent intent;
    SharedPreferences sharedpreferences;
    public static final String Student_key = "student_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        userName = findViewById(R.id.et_UserName);
        password = findViewById(R.id.et_userPassword);

        mAuth = FirebaseAuth.getInstance();

    }
    public void onClick (View view){

        login();

       // intent.putExtra("user",userName.getText().toString());
        //startActivity(intent);
    }
    public void signIn (View view){
        Intent intent;

        intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
    public void login(){
        sharedpreferences = getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE);
        String email = userName.getText().toString().trim();
        String pwd = password.getText().toString();

        if(email.isEmpty()){
            userName.setError("Enter Email");
            userName.requestFocus();
        }
        if(pwd.isEmpty()){
            password.setError("Enter Password");
            password.requestFocus();
        }
        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(mAuth.getCurrentUser().isEmailVerified()){
                    Toast.makeText(getApplicationContext(), "Successfully Login ", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getApplicationContext(),Home.class);
                   String studentId = sharedpreferences.getString(Student_key, null);
                        SharedPreferences.Editor editor = sharedpreferences.edit();


                        editor.putString(Student_key, mAuth.getCurrentUser().getUid());
                        editor.apply();
                    startActivity(intent);
                }else{
                        Toast.makeText(getApplicationContext(), "Please verify your Email", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Login failed, Check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}