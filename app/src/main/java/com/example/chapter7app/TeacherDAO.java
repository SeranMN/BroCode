package com.example.chapter7app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class TeacherDAO {

    final private DatabaseReference databaseReference;
    public TeacherDAO() {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Teachers");
    }

    public Task<Void> add(Teacher teacher){
            return databaseReference.push().setValue(teacher);
    }
    public  Task <Void> update (String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task <Void> delete (String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get (){
       return databaseReference.orderByKey();
    }
}
