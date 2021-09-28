package com.example.chapter7app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationDetails extends Fragment {
    NotificationDAO notificationDAO = new NotificationDAO();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Notification = "notification";
    private static final String ARG_Topic = "topic";
    private static final String ARG_Date = "date";
    private static final String ARG_Class = "alyear";
    private static final String ARG_Message = "message";

    // TODO: Rename and change types of parameters
    private String topic;
    private String date;
    private String alyear;
    private String message;

    public NotificationDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment NotificationDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationDetails newInstance(Notification notification) {
        NotificationDetails fragment = new NotificationDetails();
        Bundle args = new Bundle();
        args.putString(ARG_Topic,notification.getTopic());
        args.putString(ARG_Date,notification.getDate());
        args.putString(ARG_Class,notification.getALYear());
        args.putString(ARG_Message,notification.getMessage());
        args.putSerializable(ARG_Notification,notification);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            topic = getArguments().getString(ARG_Topic);
            date = getArguments().getString(ARG_Date);
            alyear = getArguments().getString(ARG_Class);
            message = getArguments().getString(ARG_Message);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification_details,container,false);
        TextView tv_Topic = (TextView) view.findViewById(R.id.tv_Topic);
        TextView tv_Date = (TextView) view.findViewById(R.id.tv_Date);
        TextView tv_Class = (TextView) view.findViewById(R.id.tv_Class);
        TextView tv_Message = (TextView) view.findViewById(R.id.tv_Message);
        tv_Topic.setText(topic);
        tv_Date.setText(date);
        tv_Class.setText(alyear);
        tv_Message.setText(message);

        Button btnUpdate = view.findViewById(R.id.btnUpdateAnnc);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Add_Notification.class);
                intent.putExtra("notification",getArguments().getSerializable(ARG_Notification));
                startActivity(intent);
            }
        });
        Button btnDelete = view.findViewById(R.id.btnDeleteAnnc);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("");
            }
            Notification notificationEdit = (Notification) getArguments().getSerializable(ARG_Notification);

            private void alert(String s){
                AlertDialog delDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Notification Details")
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
                                notificationDAO.delete(notificationEdit.getKey());
                            }
                        })
                        .create();
                delDialog.show();
            }
        });
        return view;
    }
}