package com.example.chapter7app;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class NotificationDAO {

    final private DatabaseReference databaseReference;
    public NotificationDAO(){

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Notification");
    }

    public Task<Void> add(Notification notification){
        return databaseReference.push().setValue(notification);
    }

    public Query get (){
        return databaseReference.orderByKey();
    }

}
