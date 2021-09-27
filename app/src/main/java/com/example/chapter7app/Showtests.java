package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Showtests extends AppCompatActivity {
    MarkListAdapter3 markListAdapter3;
    RecyclerView recyclerViewsecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtests);

        recyclerViewsecond=findViewById(R.id.classlist3);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Marks marks=(Marks) bundle.getSerializable("key");
        recyclerViewsecond.setLayoutManager(new LinearLayoutManager(this));
        String classid= marks.getClsID();

      /*  System.out.println(classid);*/
        /* System.out.println(classid);*/
        ArrayList<Marks> list = new ArrayList<>();
        ArrayList<String> val1 =new ArrayList<>();

        DatabaseReference database= FirebaseDatabase.getInstance().getReference("Marks");
        database.orderByChild("clsID").equalTo(classid);


        /*  markListAdapter2=new MarkListAdapter2(classid);*/
        markListAdapter3=new MarkListAdapter3(this,list);
        recyclerViewsecond.setAdapter(markListAdapter3);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Marks marks3=dataSnapshot.getValue(Marks.class);
                    if(marks3.getClsID().equals(classid) ) {


                        if(!val1.contains(marks3.getTestno())) {
                            list.add(marks3);
                        }

                        val1.add(marks3.getTestno());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}