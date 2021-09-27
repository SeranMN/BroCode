package com.example.chapter7app;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentClassAdapter extends RecyclerView.Adapter<StudentClassAdapter.ClassTutorVH> {

    private Home context;
    ArrayList <ClassTutor> list = new ArrayList<>();
    private classOnClickListener listner;
    public StudentClassAdapter(classOnClickListener listner){

        ArrayList <ClassTutor> list = new ArrayList<>();
        this.listner = listner;

    }

    public void setClasses(ArrayList <ClassTutor> classTutors){

        list.addAll(classTutors);
    }

    @NonNull
    @Override
    public ClassTutorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder ");
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_student_item,parent,false);
        ClassTutorVH holder = new ClassTutorVH(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassTutorVH holder, int position) {
        ClassTutor classTutor = list.get(position);


        holder.tutorName.setText(classTutor.getTutor().toString());
        holder.subject.setText(classTutor.getSubject());
        holder.alYear.setText(classTutor.getAlYear());
        holder.degree.setText(classTutor.getDegree());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClick(classTutor);
            }
        });

    }




    @Override
    public int getItemCount() {

        return list.size();

    }
    public class ClassTutorVH extends RecyclerView.ViewHolder{
        public TextView tutorName, degree, alYear, subject, date, time, txt_option;
        public CardView cardView;
        public ClassTutorVH(@NonNull View itemView){
            super(itemView);
            tutorName = itemView.findViewById(R.id.tvtutorNamestd);
            degree = itemView.findViewById(R.id.tvdegreestd);
            alYear = itemView.findViewById(R.id.tvalYearstd);
            subject = itemView.findViewById(R.id.tvsubjectstd);
//            date = itemView.findViewById(R.id.tvdate);
//            time = itemView.findViewById(R.id.tvtime);
           cardView = itemView.findViewById(R.id.cardv01std);
//            txt_option = itemView.findViewById(R.id.text_option);

        }
    }
    public interface classOnClickListener {
        public void onClick(ClassTutor classTutor);
    }



}