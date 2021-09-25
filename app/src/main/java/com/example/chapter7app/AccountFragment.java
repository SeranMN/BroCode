package com.example.chapter7app;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */

public class AccountFragment extends Fragment {

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student");

    

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String Student_key = "student_key";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String id;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }
    SharedPreferences sharedpreferences;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        sharedpreferences = requireActivity().getSharedPreferences("SHARED_PREFS",Context.MODE_PRIVATE);

        TextView tvName = view.findViewById(R.id.tv_stuName);
        TextView tvEmail = view.findViewById(R.id.tv_stuMail);
        TextView tvYear = view.findViewById(R.id.tv_stuYear);
       id = sharedpreferences.getString(Student_key, null);

        studentDAO dao = new studentDAO();
        reference.orderByKey().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    Student stu = snapshot.child(id).getValue(Student.class);
                    tvName.setText(stu.getName());
                    tvEmail.setText(stu.getEmail());
                    //tvYear.setText(stu.getYear());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Button login = view.findViewById(R.id.btnLogout);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.clear();

                editor.apply();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);
            }
        });


        return  view;
    }
}