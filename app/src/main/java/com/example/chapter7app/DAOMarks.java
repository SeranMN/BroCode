package com.example.chapter7app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAOMarks {

    private DatabaseReference databaseReference;
    public DAOMarks()
    {
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Marks.class.getSimpleName());

    }
    public Task<Void> add(Marks marks)
    {
         return databaseReference.push().setValue(marks);

    }
    public  Task <Void> update (String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task <Void> delete (String key){
        return databaseReference.child(key).removeValue();
    }
}
