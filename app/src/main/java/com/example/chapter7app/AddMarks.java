package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class AddMarks extends AppCompatActivity {
    TextView txtclsid;
    TextView txttestno;
    EditText etstudentID;
    EditText etmark;

    String clsid;
    String testno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        txtclsid=findViewById(R.id.txtclsid);
        txttestno=findViewById(R.id.txttestno);

        Intent intent=getIntent();
        clsid=intent.getStringExtra("clsid");
        testno=intent.getStringExtra("testno");

        txtclsid.setText(clsid);
        txttestno.setText(testno);

        etstudentID=findViewById(R.id.etstudentID);
        etmark=findViewById(R.id.etmark);

        Button btn=findViewById(R.id.btnaddanother);

        DAOMarks dao=new DAOMarks();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marks marks=new Marks(clsid,testno,etstudentID.getText().toString(),etmark.getText().toString());
                dao.add(marks).addOnSuccessListener(new OnSuccessListener<Void>() {
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
            }
        });

    }
}