package com.example.chapter7app;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class teacherListAdapter extends RecyclerView.Adapter<teacherListAdapter.TeacherVH> {

    private Home context;
    ArrayList <Teacher> list = new ArrayList<>();
   public teacherListAdapter(){
       ArrayList <Teacher> list = new ArrayList<>();
    }

    public void setTeachers(ArrayList <Teacher> teachers){

        list.addAll(teachers);
    }

    @NonNull
    @Override
    public TeacherVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder ");
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherlist,parent,false);
        TeacherVH holder = new TeacherVH(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherVH holder, int position) {
        Teacher teacher = list.get(position);
//        if (holder.txt_name == null) {
//            Log.d(TAG,teacher.getName());
//        } else {
            Log.d(TAG, "OnBindViewHolder");

            holder.txt_name.setText(teacher.getName().toString());
            holder.txt_subject.setText(teacher.getSubject());
       // }
    }




    @Override
    public int getItemCount() {

        return list.size();

    }
    public class TeacherVH extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_subject;

        public TeacherVH(@NonNull View itemView) {

            super(itemView);

            txt_name = itemView.findViewById(R.id.teacherName);
            txt_subject = (TextView) itemView.findViewById(R.id.subject);

        }
    }



    }
