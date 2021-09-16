package com.example.chapter7app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText userName;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        userName = findViewById(R.id.et_UserName);
        password = findViewById(R.id.et_userPassword);

    }
    public void onClick (View view){
        Intent intent;

        intent = new Intent(this,Home.class);
        intent.putExtra("user",userName.getText().toString());
        startActivity(intent);
    }
}