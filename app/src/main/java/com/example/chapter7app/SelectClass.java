package com.example.chapter7app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SelectClass extends AppCompatActivity {

    EditText clsid;
    EditText testno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_class);

        clsid=findViewById(R.id.etClsId);
        testno=findViewById(R.id.etClsTxtNo);
    }
    public void showaddmarks(View v){
        Intent intent;

        intent=new Intent(this,AddMarks.class);
        intent.putExtra("clsid",clsid.getText().toString());
        intent.putExtra("testno",testno.getText().toString());

            System.out.println("error");


        startActivity(intent);
    }
}