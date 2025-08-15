package com.example.mocktest_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mocktest_app.Activities.HomepageActivity;
import com.example.mocktest_app.Activities.LeaderboardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNav extends AppCompatActivity {
    View home;
    View dash;
    View logout;
    private View binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View ActivityBottomNavBinding = null;


        BottomNavigationView BnavView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        View home = findViewById(R.id.navigation_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhome();
            }
        });

        View dash = findViewById(R.id.navigation_dashboard);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendashboard();
            }
        });

        View logout = findViewById(R.id.navigation_notifications);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }
    public void openhome()
    {
        Intent intent = new Intent(BottomNav.this, HomepageActivity.class);
        startActivity(intent);
    }
    public void opendashboard()
    {
        Intent intent = new Intent(BottomNav.this, LeaderboardActivity.class);
        startActivity(intent);
    }
    public void Logout()
    {
    }

}