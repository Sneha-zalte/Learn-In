package com.example.mocktest_app.SubjectsPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mocktest_app.Activities.LeaderboardActivity;
import com.example.mocktest_app.Activities.MyAccount;
import com.example.mocktest_app.R;
import com.example.mocktest_app.categoryFragment;

public class Mocktestsub extends AppCompatActivity {

    private FrameLayout main_frame;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mocktestsub);

        main_frame = (FrameLayout) findViewById(R.id.Main_frame);
        setFragment();

        View myaccount = findViewById(R.id.nav_account);
        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmyAccount();
            }
        });

        View leaderB = findViewById(R.id.nav_leaderboard);
        leaderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderb();
            }
        });
    }

    public void setFragment()
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Main_frame,new categoryFragment());
        ft.commit();
    }

    public void openmyAccount()
    {
        Intent intent = new Intent(Mocktestsub.this, MyAccount.class);
        startActivity(intent);
    }

    public void openLeaderb()
    {
        Intent intent = new Intent(Mocktestsub.this, LeaderboardActivity.class);
        startActivity(intent);
    }



}