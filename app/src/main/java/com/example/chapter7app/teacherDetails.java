package com.example.chapter7app;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link teacherDetails#newInstance} factory method to
 * create an instance of this fragment.
 */

public class teacherDetails extends Fragment {
    TeacherDAO teacherDAO = new TeacherDAO();



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Teacher ="Teacher";
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_MAIL = "mail";
    private static final String ARG_PHONE= "mob";
    private static final String ARG_NAME = "name";
//    private static final String ARG_ADDRESS = "";

    // TODO: Rename and change types of parameters
    private String name;
    private String address;
    private String phone;
    private String mail;


    public teacherDetails() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment teacherDetails.
     */
    // TODO: Rename and change types and number of parameters
    public static teacherDetails newInstance(Teacher teacher) {
        teacherDetails fragment = new teacherDetails();
        Bundle args = new Bundle();
        Teacher t = teacher;
        args.putString(ARG_NAME, teacher.getName());
        args.putString(ARG_ADDRESS,teacher.getAddress());
        args.putString(ARG_MAIL,teacher.getEmail());
        args.putString(ARG_PHONE,teacher.getMob());
        args.putSerializable(ARG_Teacher,teacher);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            address = getArguments().getString(ARG_ADDRESS);
            phone= getArguments().getString(ARG_PHONE);
            mail = getArguments().getString(ARG_MAIL);
            //Log.d(TAG,name);

        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_teacher_details, container, false);
        TextView tvName = (TextView) view.findViewById(R.id.TeacherName);
        TextView tvAddress = (TextView)view.findViewById(R.id.tv_address);
        TextView tvphone = (TextView) view.findViewById(R.id.tvPhone);
        TextView tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvName.setText(name);
        tvAddress.setText(address);
        tvphone.setText(phone);
        tvEmail.setText(mail);

        Button edit = view.findViewById(R.id.btnUpdate);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),addTeacher.class);
                intent.putExtra("teacher",getArguments().getSerializable(ARG_Teacher));
                startActivity(intent);
            }
        });
        Button delete = view.findViewById(R.id. btnDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert("");


            }
            Teacher teacherEdit = (Teacher) getArguments().getSerializable(ARG_Teacher);

            private void alert(String s) {
                AlertDialog dlg = new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Teacher")
                        .setMessage("Do you want to delete "+teacherEdit.getName())

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                               teacherDAO.delete(teacherEdit.getKey());
                                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.frameLayout2,new AdminTeacherFragment());
                                fragmentTransaction.commit();

                            }
                        })
                        .create();
                dlg.show();
            }
        });

        return  view;
    }
}