package com.example.chapter7app;
import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassTutorVH> {

    private Context context;
    ArrayList <ClassTutor> list = new ArrayList<>();
    private classOnClickListener listener;
    public ClassAdapter(classOnClickListener listener){

        ArrayList <ClassTutor> list = new ArrayList<>();
        this.listener = listener;
    }

    public void setClasses(ArrayList <ClassTutor> classTutors){
        list.addAll(classTutors);
    }

    @NonNull
    @Override
    public ClassTutorVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        ClassTutorVH holder = new ClassTutorVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassTutorVH holder, int position) {
        ClassTutor classTutor = list.get(position);
        Log.d(TAG, "OnBindViewHolder");
        holder.clsid.setText(classTutor.getClsid().toString());
        holder.tutorName.setText(classTutor.getTutor().toString());
        holder.degree.setText(classTutor.getDegree().toString());
        holder.alYear.setText(classTutor.getAlYear().toString());
        holder.subject.setText(classTutor.getSubject().toString());
        holder.date.setText(classTutor.getDate().toString());
        holder.time.setText(classTutor.getTime().toString());


        holder.txt_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                PopupMenu popupMenu = new PopupMenu(v.getContext(), holder.txt_option);
                popupMenu.inflate(R.menu.option_class_menu);
                popupMenu.setOnMenuItemClickListener (item ->
                {
                    switch (item.getItemId()) {
                        case R.id.menu_edit:
                            Intent intent = new Intent(v.getContext(), AddClass.class);
                            intent.putExtra("Edit", classTutor);
                            v.getContext().startActivity(intent);
                            break;
                        case R.id.menu_remove:
                            showAlertDialog(v.getContext());


                            break;
                    }
                    return false;


                });
                popupMenu.show();
            }

            private void showAlertDialog(Context context){
                AlertDialog delDialog = new AlertDialog.Builder(context)
                        .setTitle("Delete Class Details")
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
                                DAOClassTutor dao = new DAOClassTutor();
                                dao.remove(classTutor.getKey()).addOnSuccessListener(suc ->
                                {
                                    Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
                                    notifyItemRemoved(position);
                                    list.remove(classTutor);
                                }).addOnFailureListener(er ->
                                {
                                    Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
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

    public class ClassTutorVH extends RecyclerView.ViewHolder{
        public TextView clsid,tutorName, degree, alYear, subject, date, time, txt_option;
        public CardView cardView;
        public ClassTutorVH(@NonNull View itemView){
            super(itemView);
            clsid=itemView.findViewById(R.id.claid);
            tutorName = itemView.findViewById(R.id.tvtutorName);
            degree = itemView.findViewById(R.id.tvdegree);
            alYear = itemView.findViewById(R.id.tvalYear);
            subject = itemView.findViewById(R.id.tvsubject);
            date = itemView.findViewById(R.id.tvdate);
            time = itemView.findViewById(R.id.tvtime);
            cardView = itemView.findViewById(R.id.cardv01);
            txt_option = itemView.findViewById(R.id.text_option);


        }
    }

    public interface classOnClickListener{
        public void onClick(ClassTutor classTutor);
    }

}
//package com.example.chapter7app;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHolder> {
//
//    Context context;
//
//    ArrayList<ClassTutor> list;
//
//    public ClassAdapter(Context context, ArrayList<ClassTutor> list) {
//        this.context = context;
//        this.list = list;
//}
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.layout_item,parent,false);
//        return  new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        ClassTutor user = list.get(position);
//        holder.tutorName.setText(user.getTutor());
//        holder.degree.setText(user.getDegree());
//        holder.alYear.setText(user.getAlYear());
//        holder.subject.setText(user.getSubject());
//        holder.date.setText(user.getDate());
//        holder.time.setText(user.getTime());
      //  UPDATE AND DELETE
//        vh.txt_option.setOnClickListener(v->
//        {
//            PopupMenu popupMenu =new PopupMenu(context,vh.txt_option);
//            popupMenu.inflate(R.menu.option_class_menu);
//            popupMenu.setOnMenuItemClickListener(item->
//            {
//                switch (item.getItemId())
//                {
//                    case R.id.menu_edit:
//                        Intent intent=new Intent(context,MainActivity.class);
//                        intent.putExtra("EDIT",cla);
//                        context.startActivity(intent);
//                        break;
//                    case R.id.menu_remove:
//                        DAOEmployee dao=new DAOClassTutor();
//                        dao.remove(cla.getKey()).addOnSuccessListener(suc->
//                        {
//                            Toast.makeText(context, "Record is removed", Toast.LENGTH_SHORT).show();
//                            notifyItemRemoved(position);
//                            list.remove(cla);
//                        }).addOnFailureListener(er->
//                        {
//                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
//                        });
//
//                        break;
//                }
//                return false;
//            });
//            popupMenu.show();
//        });

//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView tutorName, degree, alYear, subject, date, time;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            tutorName = itemView.findViewById(R.id.tvtutorName);
//            degree = itemView.findViewById(R.id.tvdegree);
//            alYear = itemView.findViewById(R.id.tvalYear);
//            subject = itemView.findViewById(R.id.tvsubject);
//            date = itemView.findViewById(R.id.tvdate);
//            time = itemView.findViewById(R.id.tvtime);
//
//        }
//    }
//
//}
//
//
//
