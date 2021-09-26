package com.example.chapter7app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class AdminAddMarksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminAddMarksFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AdminAddMarksFragment newInstance(String param1, String param2) {
        AdminAddMarksFragment fragment = new AdminAddMarksFragment();
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
       View v= inflater.inflate(R.layout.fragment_admin_add_marks, container, false);
        Button btn=(Button) v.findViewById(R.id.btnaddmarks);
        Button btn2=(Button) v.findViewById(R.id.btnview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),SelectClass.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fr =getFragmentManager().beginTransaction();
                fr.replace(R.id.frameLayout2,new DisplayMarksFragment());
                fr.commit();
            }
        });
        return v;
    }

}