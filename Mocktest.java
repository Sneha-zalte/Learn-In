package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mocktest_app.R;
import com.example.mocktest_app.categoryFragment;
import com.example.mocktest_app.dbquery;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Mocktest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private BottomNavigationView bottomNavigationView;
    private FrameLayout main_frame;
    private TextView drawerprofilename;
    private TextView drawerprofiletxt;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener()
            {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                   return false;
                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bottomNavigationView = findViewById(R.id.bottomNavView);
        main_frame = findViewById(R.id.main_frame);
        setFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_mocktest);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        drawerprofilename = navigationView.getHeaderView(0).findViewById(R.id.navdrawername);
        drawerprofiletxt = navigationView.getHeaderView(0).findViewById(R.id.navdrawertextimg);
        String name = dbquery.myprofile.getName();
        drawerprofilename.setText(name);

        drawerprofiletxt.setText(name.toUpperCase().substring(0,1));

        View myaccount = findViewById(R.id.nav_account);
        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmyAccount();
            }
        });
        View leaderb = findViewById(R.id.nav_leaderboard);
        leaderb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mocktest.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_mocktest);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.nav_home){

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_account) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.main_frame,new categoryFragment());
        ft.commit();
    }

    public void openmyAccount()
    {
        Intent intent = new Intent(Mocktest.this, MyAccount.class);
        startActivity(intent);
    }
}