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

import java.util.HashMap;

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

        getIntent().getSerializableExtra("Edit");
        Marks editmarks=(Marks) getIntent().getSerializableExtra("Edit");
        if(editmarks!=null){
            btn.setText("Update");
            etstudentID.setText(editmarks.getStudentID());
            etmark.setText(editmarks.getMark());
            txtclsid.setText(editmarks.getClsID());
            txttestno.setText(editmarks.getTestno());

        }
        else{
            btn.setText("Add");

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marks marks=new Marks(clsid,testno,etstudentID.getText().toString(),etmark.getText().toString());
                if(editmarks==null) {
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
                else{
                    HashMap<String,Object> hashMap =new HashMap<>();
                    hashMap.put("studentID",etstudentID.getText().toString());
                    hashMap.put("mark",etmark.getText().toString());
                    hashMap.put("clsID",txtclsid.getText().toString());
                    hashMap.put("testno",txttestno.getText().toString());
                    System.out.println(editmarks.getKey());

                    dao.update(editmarks.getKey(),hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Record is updated", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

    }
}