package com.example.chapter7app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
   Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , Login.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }


}