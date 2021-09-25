package com.example.chapter7app;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class AdminClassFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public AdminClassFragment() {
        // Required empty public constructor
    }

    public AdminClassFragment(ClassAdapter adapter){
        this.adapter = adapter;
    }


    // TODO: Rename and change types and number of parameters
    public static AdminClassFragment newInstance(String param1, String param2) {
        AdminClassFragment fragment = new AdminClassFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    DAOClassTutor DAOClassTutor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ClassAdapter adapter = new ClassAdapter(null);
    TextView tvClassDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_class, container, false);
        Button button = (Button) view.findViewById(R.id.btn_addnewclass);
        DAOClassTutor = new DAOClassTutor();
        loadData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddClass.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                tvClassDetails = (TextView) view.findViewById(R.id.tvClassDetails);
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ClassListAdmin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }

    View view;
    public void loadData(){
        DAOClassTutor.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ClassTutor> classTutors = new ArrayList<>();
                ClassTutor classTutor = new ClassTutor();
                ArrayList<ClassTutor> not = classTutors;
                if (snapshot.hasChildren()){
                    for (DataSnapshot data : snapshot.getChildren()){
                        classTutor = data.getValue(ClassTutor.class);
                        classTutor.setKey(data.getKey());
                        classTutors.add(classTutor);
                    }
                    adapter.setClasses(classTutors);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


//    @Override
//    public void onClick(ClassTutor notification) {
//        Fragment fragment = NotificationDetails.newInstance(notification);
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frameLayout2,fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }
}



