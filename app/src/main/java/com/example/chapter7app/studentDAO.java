package com.example.chapter7app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class studentDAO {
    final private DatabaseReference databaseReference;


    public  studentDAO(){
        FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Student");
    }
    public Task <Void> add(Student student,String ID){
        return databaseReference.child(ID).setValue(student);
    }
    public Query get (String id){
        return databaseReference.orderByKey().equalTo(id);
    }
}
