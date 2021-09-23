package com.example.chapter7app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TeacherDAO {

    final private DatabaseReference databaseReference;
    public TeacherDAO() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Teachers");
    }

    public Task<Void> add(Teacher teacher){
            return databaseReference.push().setValue(teacher);
    }

    public Query get (){
       return databaseReference.orderByKey();
    }
}
