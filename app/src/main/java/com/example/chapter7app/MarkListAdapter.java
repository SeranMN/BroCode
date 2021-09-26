package com.example.chapter7app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MarkListAdapter extends RecyclerView.Adapter<MarkListAdapter.MyViewHolder> {
    Context context;

    ArrayList<Marks> list;

    public MarkListAdapter(Context context, ArrayList<Marks> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MarkListAdapter.MyViewHolder holder, int position) {
        Marks marks=list.get(position);
        holder.classID.setText(marks.getClsID());
        holder.testno.setText(marks.getTestno());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ShowMarks.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("key",marks);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView classID,testno,stID,mark;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            classID=itemView.findViewById(R.id.clsidn);
            testno=itemView.findViewById(R.id.testnonn);


        }
    }




}
