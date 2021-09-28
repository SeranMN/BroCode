package com.example.chapter7app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudenttestMarks extends AppCompatActivity {
    String id;
    MarkListAdapter4 markListAdapter3;
    RecyclerView recyclerViewsecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_studenttest_marks);
        recyclerViewsecond=findViewById(R.id.classlist4);
        id=getIntent().getStringExtra("key");
        System.out.println(id);
        recyclerViewsecond.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Marks> list = new ArrayList<>();
        ArrayList<String> val1 =new ArrayList<>();

        DatabaseReference database= FirebaseDatabase.getInstance().getReference("Marks");



        /*  markListAdapter2=new MarkListAdapter2(classid);*/
        markListAdapter3=new MarkListAdapter4(this,list);
        recyclerViewsecond.setAdapter(markListAdapter3);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Marks marks3=dataSnapshot.getValue(Marks.class);
                    if(marks3.getClsID().equals(id) ) {


                        if(!val1.contains(marks3.getTestno())) {
                            list.add(marks3);
                            System.out.println(marks3.getTestno());
                        }

                        val1.add(marks3.getTestno());
                    }
                    markListAdapter3.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
