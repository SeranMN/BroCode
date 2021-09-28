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


public class MarkListAdapter4 extends RecyclerView.Adapter {
    /* public MarkListAdapter2(List<String> list) {
         this.list = list;
     }

     List<String> list;*/
    ArrayList<Marks> list;
    Context context;

    public MarkListAdapter4(Context context, ArrayList<Marks> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item4,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
        Marks marks=list.get(position);
       /* String Classid=viewHolderClass.
        String testno=marks.getTestno();*/
        System.out.println("Seran");

        viewHolderClass.tmarks.setText(marks.getTestno());

        viewHolderClass.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),ShowMarksstudent.class);
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
    public static class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView tmarks;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);

            tmarks=itemView.findViewById(R.id.testnoss);
           /* stID=itemView.findViewById(R.id.stidnn);
            mark=itemView.findViewById(R.id.marksnn);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);*/


        }
    }
}
