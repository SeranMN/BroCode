package com.example.chapter7app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOClassTutor {
    private DatabaseReference databaseReference;

    public DAOClassTutor() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ClassTutor.class.getSimpleName());
    }

    public Task<Void> add(ClassTutor cla) {
        return databaseReference.push().setValue(cla);
    }

    public Query get() {
        return databaseReference.orderByKey();

    }
}
