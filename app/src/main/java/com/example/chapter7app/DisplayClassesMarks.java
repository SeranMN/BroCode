/*
package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayClassesMarks extends AppCompatActivity {

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_classes_marks);
        Button btn =(Button) findViewById(R.id.btncls1);

        db= FirebaseDatabase.getInstance().getReference().child("Marks").child("clsID");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot marksDatasnap: snapshot.getChildren() ){
                    Marks  marks=marksDatasnap.getValue(Marks.class);

                }
                Log.d(TAG,"marks");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}*/
