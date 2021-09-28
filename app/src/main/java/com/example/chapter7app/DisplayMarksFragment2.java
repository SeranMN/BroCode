package com.example.chapter7app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMarksFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String testno,studentid,marks;

    public DisplayMarksFragment2(){}

    public DisplayMarksFragment2(String testno,String studentid,String marks) {
       this.testno=testno;
       this.studentid=studentid;
       this.marks=marks;
    }


    // TODO: Rename and change types and number of parameters
    public static DisplayMarksFragment2 newInstance(String param1, String param2) {
        DisplayMarksFragment2 fragment = new DisplayMarksFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_display_marks2, container, false);

      TextView testno1=v.findViewById(R.id.tstno);
      TextView studentid1=v.findViewById(R.id.stid);
      TextView mark1=v.findViewById(R.id.mar);

      testno1.setText(testno);
      studentid1.setText(studentid);
      mark1.setText(marks);
      return v;
    }
}