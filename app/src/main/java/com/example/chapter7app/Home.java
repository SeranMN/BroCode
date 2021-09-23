package com.example.chapter7app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       MeowBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_class_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_launcher));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_baseline_notifications_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.ic_baseline_account_circle_24));

        String user = bundle.getString("user");

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        if(user.equals("admin")){
                            fragment = new AdminHomeFragment();
                        }else {
                            fragment = new HomeFragment();
                        }
                        getSupportActionBar().setTitle("Home");
                        break;
                    case 2:
                        if(user.equals("admin")){
                            fragment = new AdminClassFragment();
                        }else {
                            fragment = new ClassesFragment();
                        }
                        getSupportActionBar().setTitle("Classes");
                        break;
                    case 3:
                        if(user.equals("admin")){
                            fragment = new AdminTeacherFragment();
                        }else {
                            fragment = new TeachersFragment();
                        }
                        getSupportActionBar().setTitle("Teachers");
                        break;
                    case 4:
                        if(user.equals("admin")){
                            fragment = new AdminNotificationFragment();
                        }else{
                            fragment = new NotificationFragment();
                        }

                        getSupportActionBar().setTitle("Notifications");
                        break;
                    case 5:

                        fragment = new AccountFragment();
                        getSupportActionBar().setTitle("Account");

                }
                loadFragment(fragment);

            }
        });
        //set notification count
        bottomNavigation.setCount(4,"10");

        //set home initial
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });





    }
    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}