package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.HashMap;

public class AddClass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        EditText edit_ClassID =findViewById(R.id.txtclaid);
        EditText edit_TutorName = findViewById(R.id.edtxt01);
        EditText edit_Degree = findViewById(R.id.edtxt04);
        EditText edit_ALYear = findViewById(R.id.edtxtn01);
        EditText edit_Subject = findViewById(R.id.edtxt03);
        EditText edit_Date = findViewById(R.id.edtxtd01);
        EditText edit_Time = findViewById(R.id.edtxtt01);
        Button btn = findViewById(R.id.btn04);
//        btn.setOnClickListener(v->
//        {
//            Intent intent =new Intent(this, RVActivity.class);
//            startActivity(intent);
//        });
        DAOClassTutor dao = new DAOClassTutor();
        ClassTutor cla_edit = (ClassTutor)getIntent().getSerializableExtra("Edit");
        if(cla_edit !=null)
        {
            btn.setText("UPDATE");
            edit_ClassID.setText(cla_edit.getClsid());

            edit_TutorName.setText(cla_edit.getTutor());
            edit_Degree.setText(cla_edit.getDegree());
            edit_ALYear.setText(cla_edit.getAlYear());
            edit_Subject.setText(cla_edit.getSubject());
            edit_Date.setText(cla_edit.getDate());
            edit_Time.setText(cla_edit.getTime());

        }
        else
        {
            btn.setText("SUBMIT");
            btn.setVisibility(View.VISIBLE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassTutor cla = new ClassTutor
                        (
                                edit_ClassID.getText().toString(),
                                edit_TutorName.getText().toString(),
                                edit_Degree.getText().toString(),
                                edit_ALYear.getText().toString(),
                                edit_Subject.getText().toString(),
                                edit_Date.getText().toString(),
                                edit_Time.getText().toString()
                        );
                if (cla.getClsid().isEmpty()) {
                    edit_ClassID.setError("Enter ClsID");
                    edit_ClassID.requestFocus();
                    return;
                }
                if (cla.getTutor().isEmpty()){
                    edit_TutorName.setError("Enter Tutor");
                    edit_TutorName.requestFocus();
                    return;
                }
                if (cla.getDegree().isEmpty()){
                    edit_Degree.setError("Enter Degree");
                    edit_Degree.requestFocus();
                    return;
                }
                if (cla.getAlYear().isEmpty()){
                    edit_ALYear.setError("Enter AL year");
                    edit_ALYear.requestFocus();
                    return;
                }
                if (cla.getSubject().isEmpty()){
                    edit_Subject.setError("Enter Subject");
                    edit_Subject.requestFocus();
                    return;
                }
                if (cla.getDate().isEmpty()){
                    edit_Date.setError("Enter Date");
                    edit_Date.requestFocus();
                    return;
                }
                if (cla.getTime().isEmpty()){
                    edit_Time.setError("Enter Tutor");
                    edit_Time.requestFocus();
                    return;
                }



                if(cla_edit==null) {
                    dao.add(cla).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Class Added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("clsid",edit_ClassID.getText().toString());
                    hashMap.put("tutor", edit_TutorName.getText().toString());
                    hashMap.put("degree", edit_Degree.getText().toString());
                    hashMap.put("alYear", edit_ALYear.getText().toString());
                    hashMap.put("subject", edit_Subject.getText().toString());
                    hashMap.put("date", edit_Date.getText().toString());
                    hashMap.put("time", edit_Time.getText().toString());
                    dao.update(cla_edit.getKey(), hashMap).addOnSuccessListener(suc ->
                    {
                        Toast.makeText(getApplicationContext(), "Record is updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }).addOnFailureListener(er ->
                    {
                        Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            };

        });

    }
}