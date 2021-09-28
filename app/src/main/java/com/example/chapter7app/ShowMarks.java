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

public class ShowMarks extends AppCompatActivity {
    MarkListAdapter2 markListAdapter2;
    RecyclerView recyclerViewsecond;
    TextView avrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_marks);

        recyclerViewsecond=findViewById(R.id.classlist2);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Marks marks=(Marks) bundle.getSerializable("key");
        recyclerViewsecond.setLayoutManager(new LinearLayoutManager(this));
        String classid= marks.getClsID();
        String testno=marks.getTestno();
        System.out.println(classid);
       /* System.out.println(classid);*/
        ArrayList<Marks> list = new ArrayList<>();
        avrg=findViewById(R.id.avg);

        DatabaseReference database= FirebaseDatabase.getInstance().getReference("Marks");
            database.orderByChild("clsID").equalTo(classid);


      /*  markListAdapter2=new MarkListAdapter2(classid);*/
        markListAdapter2=new MarkListAdapter2(this,list);
        recyclerViewsecond.setAdapter(markListAdapter2);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Double sum=0.00;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Marks marks3=dataSnapshot.getValue(Marks.class);
                    if(marks3.getClsID().equals(classid) && marks3.getTestno().equals(testno)) {

                        sum= Double.valueOf(marks3.getMark())+sum;
                        System.out.println(list);
                        marks3.setKey(dataSnapshot.getKey());
                        list.add(marks3);
                        System.out.println(sum);
                    }

                }
                Double avg=sum/list.size();
                markListAdapter2.notifyDataSetChanged();
                avrg.setText(avg.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}