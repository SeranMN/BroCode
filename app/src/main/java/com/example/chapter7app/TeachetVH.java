package com.example.chapter7app;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;


public class TeachetVH extends RecyclerView.ViewHolder {
 public TextView txt_name,txt_subject;

    public TeachetVH(@NonNull View itemView) {
        super(itemView);

        txt_name = (TextView) itemView.findViewById(R.id.teacherName);
        txt_subject = (TextView) itemView.findViewById(R.id.subject);
    }
}
