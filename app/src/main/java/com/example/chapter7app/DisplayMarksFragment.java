package com.example.chapter7app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayMarksFragment extends Fragment {



    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public DisplayMarksFragment() {
        // Required empty public constructor
    }



    public static DisplayMarksFragment newInstance(String param1, String param2) {
        DisplayMarksFragment fragment = new DisplayMarksFragment();
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
        View view= inflater.inflate(R.layout.fragment_display_marks, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.classlist);
        DatabaseReference db=(DatabaseReference) FirebaseDatabase.getInstance().getReference("Marks");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Marks> list = new ArrayList<>();
        ArrayList<String> val =new ArrayList<>();


        MarkListAdapter markListAdapter=new MarkListAdapter(getActivity(),list);
        recyclerView.setAdapter(markListAdapter);
/*
        RecyclerView recyclerView2=(RecyclerView) view.findViewById(R.id.classlist);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));*/

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Marks marks=dataSnapshot.getValue(Marks.class);
                    if(!val.contains(marks.getClsID())) {
                        list.add(marks);
                    }
                    val.add(marks.getClsID());
                }


                markListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}

