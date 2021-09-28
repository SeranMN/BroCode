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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminNotificationFragment extends Fragment implements NotificationListAdapter.notificationOnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminNotificationFragment() {
        // Required empty public constructor
    }

    public AdminNotificationFragment(NotificationListAdapter adapter){
        this.adapter = adapter;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminNotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminNotificationFragment newInstance(String param1, String param2) {
        AdminNotificationFragment fragment = new AdminNotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    NotificationDAO notificationDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    NotificationListAdapter adapter = new NotificationListAdapter(this);
    TextView tvAnnouncement;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_notification, container, false);
        Button button = (Button) view.findViewById(R.id.btn_addnewnoti);
        progressBar = view.findViewById(R.id.progressBar2);

        notificationDAO = new NotificationDAO();
        loadData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Add_Notification.class);
                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                tvAnnouncement = (TextView) view.findViewById(R.id.tvAnnouncement);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.NotificationListAdmin);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    View view;
    public void loadData(){
        notificationDAO.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Notification> notifications = new ArrayList<>();
                Notification notification = new Notification();

                ArrayList<Notification> not = notifications;
                if (snapshot.hasChildren()){
                    for (DataSnapshot data : snapshot.getChildren()){
                        progressBar.setVisibility(View.VISIBLE);
                        notification = data.getValue(Notification.class);

                        notification.setKey(data.getKey());
                        notifications.add(notification);
                    }
                    progressBar.setVisibility(View.GONE);
                    adapter.setNotifications(notifications);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onClick(Notification notification) {
        Fragment fragment = NotificationDetails.newInstance(notification);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout2,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}