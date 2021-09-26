package com.example.chapter7app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;


public class MarkListAdapter2 extends RecyclerView.Adapter {
   /* public MarkListAdapter2(List<String> list) {
        this.list = list;
    }

    List<String> list;*/
   ArrayList<Marks> list;
   Context context;

    public MarkListAdapter2(Context context, ArrayList<Marks> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass) holder;
        Marks marks=list.get(position);
       /* String Classid=viewHolderClass.
        String testno=marks.getTestno();*/

        viewHolderClass.stID.setText(marks.getStudentID());
        viewHolderClass.mark.setText(marks.getMark());

        viewHolderClass.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent =new Intent(context,AddMarks.class);
               intent.putExtra("Edit",marks);
               context.startActivity(intent);
            }
        });

        viewHolderClass.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                showAlertDialog(v2.getContext());
            }

            private void showAlertDialog(Context context){

                AlertDialog delDialog = new AlertDialog.Builder(context)
                        .setTitle("Remove Mark")
                        .setMessage("Are you sure?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DAOMarks dao=new DAOMarks();

                                dao.delete(marks.getKey()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                        Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                                        notifyItemRemoved(position);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }

                        })
                        .create();
                delDialog.show();

            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolderClass extends RecyclerView.ViewHolder{
        TextView classID,testno,stID,mark,edit,delete;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);


            stID=itemView.findViewById(R.id.stidnn);
            mark=itemView.findViewById(R.id.marksnn);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);


        }
    }
}
