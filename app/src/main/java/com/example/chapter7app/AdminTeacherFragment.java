package com.example.chapter7app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminTeacherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminTeacherFragment extends Fragment implements teacherListAdapter.teachrOnClickLisner{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminTeacherFragment() {
        // Required empty public constructor
    }

    public AdminTeacherFragment(teacherListAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminTeacherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminTeacherFragment newInstance(String param1, String param2) {
        AdminTeacherFragment fragment = new AdminTeacherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    TeacherDAO teacherDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    teacherListAdapter adapter = new teacherListAdapter(this);
    TextView tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_admin_teacher, container, false);
        Button button = (Button) view.findViewById(R.id.btn_add);

        System.out.print("teachers");
        teacherDAO = new TeacherDAO();

        loadData();


        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.frameLayout2,new AddTeacherFragment());
//                fr.commit();
                Intent intent;
                intent = new Intent(getActivity(), addTeacher.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 tv = (TextView) view.findViewById(R.id.tv);

            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.teacherListAdmin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);


        recyclerView.setAdapter(adapter);


        return view;
    }
View view;
    public void loadData() {
        teacherDAO.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Teacher> teachers = new ArrayList( );
                Teacher teacher = new Teacher();



                ArrayList<Teacher> t =  teachers;
                if (snapshot.hasChildren()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                         teacher = data.getValue(Teacher.class);

                         teacher.setKey(data.getKey());
                        teachers.add(teacher);


                    }

                    adapter.setTeachers(teachers);

                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onClick(Teacher teacher) {
        Fragment fragment = teacherDetails.newInstance(teacher);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout2,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
