package com.example.chapter7app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationDetailsStudent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationDetailsStudent extends Fragment {

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

    public NotificationDetailsStudent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment NotificationDetailsStudent.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationDetailsStudent newInstance(Notification notification) {
        NotificationDetailsStudent fragment = new NotificationDetailsStudent();
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

        View view = inflater.inflate(R.layout.fragment_notification_details_student, container, false);
        TextView tv_Topic = (TextView) view.findViewById(R.id.tv_Topic);
        TextView tv_Date = (TextView) view.findViewById(R.id.tv_Date);
        TextView tv_Class = (TextView) view.findViewById(R.id.tv_Class);
        TextView tv_Message = (TextView) view.findViewById(R.id.tv_Message);
        tv_Topic.setText(topic);
        tv_Date.setText(date);
        tv_Class.setText(alyear);
        tv_Message.setText(message);

        return view;
    }
}