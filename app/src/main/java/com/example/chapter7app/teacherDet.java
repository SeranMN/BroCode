package com.example.chapter7app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class teacherDet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Teacher = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
 Teacher teacher ;
    public teacherDet() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static teacherDet newInstance(Teacher teacher) {
        teacherDet fragment = new teacherDet();
        Bundle args = new Bundle();
        args.putSerializable(ARG_Teacher, teacher);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
          teacher = (Teacher) getArguments().getSerializable(ARG_Teacher);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teacher_det, container, false);

        TextView name = view.findViewById(R.id.tv_name);
         TextView subject = view.findViewById(R.id.tv_sub);
         TextView degree = view.findViewById(R.id.tv_degree);
            name.setText(teacher.getName());
            subject.setText(teacher.getSubject());
            degree.setText(teacher.getDegree());

        return view;
    }
}