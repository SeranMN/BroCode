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

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationVH> {

    private Home context;
    ArrayList <Notification> list = new ArrayList<>();
    private notificationOnClickListener listener;

    public NotificationListAdapter(notificationOnClickListener listener){

        ArrayList <Notification> list = new ArrayList<>();
        this.listener = listener;
    }

    public void setNotifications(ArrayList <Notification> notifications){
        list.addAll(notifications);
    }

    @NonNull
    @Override
    public NotificationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list,parent,false);
        NotificationVH holder = new NotificationVH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationVH holder, int position) {
        Notification notification = list.get(position);
        Log.d(TAG,"OnBindViewHolder");

        holder.txtTopic.setText(notification.getTopic().toString());
        holder.txtDate.setText(notification.getDate().toString());
        holder.txtALYear.setText(notification.getALYear());
        holder.txtMessage.setText(notification.getMessage().toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(notification);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NotificationVH extends RecyclerView.ViewHolder{
        public TextView txtTopic, txtDate, txtALYear, txtMessage;
        public CardView cardView;

        public NotificationVH(@NonNull View itemView){
            super(itemView);

            txtTopic = itemView.findViewById(R.id.viewTopic);
            txtDate = itemView.findViewById(R.id.viewDate);
            txtALYear = itemView.findViewById(R.id.viewALClass);
            txtMessage = itemView.findViewById(R.id.viewMessage);
            cardView = itemView.findViewById(R.id.notificationCard);
        }
    }

    public interface notificationOnClickListener{
        public void onClick(Notification notification);
    }

}
