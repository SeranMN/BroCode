package com.example.chapter7app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ClassDetails extends Fragment {

    DAOClassTutor DAO;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private  static final String ARG_Id="id";
    private static final String ARG_ClassTutor = "ClassTutor";
    private static final String ARG_Topic = "Topic";
    private static final String ARG_Tutor = "Tutor";
    private static final String ARG_Degree = "Degree";
    private static final String ARG_Date = "Date";
    private static final String ARG_Time = "Time";

    // TODO: Rename and change types of parameters
    private String Topic;
    private String Tutor;
    private String Degree;
    private String Date;
    private String Time;
    private String clsid;
    public ClassDetails() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ClassDetails newInstance(ClassTutor classTutor) {
        ClassDetails fragment = new ClassDetails();
        Bundle args = new Bundle();
        args.putString(ARG_Id,classTutor.getClsid());
        args.putString(ARG_Topic, classTutor.getAlYear()  +classTutor.getSubject());
        args.putString(ARG_Tutor, classTutor.getTutor());
        args.putString(ARG_Degree, classTutor.getDegree());
        args.putString(ARG_Date, classTutor.getDate());
        args.putString(ARG_Time, classTutor.getTime());
        args.putSerializable(ARG_ClassTutor, classTutor);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clsid=getArguments().getString(ARG_Id);
            Topic = getArguments().getString(ARG_Topic);
            Tutor = getArguments().getString(ARG_Tutor);
            Degree = getArguments().getString(ARG_Degree);
            Date = getArguments().getString(ARG_Date);
            Time = getArguments().getString(ARG_Time);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_class_details, container, false);

        TextView txtclassname=(TextView)  view.findViewById(R.id.txtclassname);
        TextView txttutorname=(TextView)  view.findViewById(R.id.txttutor);
        TextView txtdegree=(TextView)  view.findViewById(R.id.txtdegree);
        TextView txtdate=(TextView)  view.findViewById(R.id.txtdate);
        TextView txttime=(TextView)  view.findViewById(R.id.txttime);

        txtclassname.setText(Topic);
        txttutorname.setText(Tutor);
        txtdegree.setText(Degree);
        txtdate.setText(Date);
        txttime.setText(Time);

        Button btnenroll=view.findViewById(R.id.btnenroll);
        Button btnviewmark=view.findViewById(R.id.buttonvim);

        btnviewmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(),StudenttestMarks.class);

                intent.putExtra("key",clsid);
                System.out.println(clsid);
                v.getContext().startActivity(intent);
            }
        });

//        btnenroll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


       return view;
    }

}