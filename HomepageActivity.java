package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.SubjectsPage.Mocktestsub;
import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.SubjectsPage.Subject;
import com.example.mocktest_app.SubjectsPage.previousyrsubject;
import com.example.mocktest_app.SubjectsPage.videosubject;
import com.example.mocktest_app.dbquery;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomepageActivity extends AppCompatActivity{

    ImageButton btn1,btn2,btn3,btn4,btn5,btn6;
    View home;
    View dash;
    View myaccount;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btn1 = (ImageButton) findViewById(R.id.notes);
        btn2 = (ImageButton) findViewById(R.id.videolec);
        btn3 = (ImageButton) findViewById(R.id.prepaper);
        btn4 = (ImageButton) findViewById(R.id.mock);
        btn5 = (ImageButton) findViewById(R.id.subsciption);
        btn6 = (ImageButton) findViewById(R.id.doubts);
        bottomNavigationView = findViewById(R.id.nav_view);
        home = findViewById(R.id.nav_home);
        dash = findViewById(R.id.navigation_dashboard);
        myaccount = findViewById(R.id.navigation_notifications);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepapers();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mocktest();
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscription();
            }
        });

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

        View myaccount = findViewById(R.id.navigation_notifications);
        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAccount();
            }
        });

    }
    public void notes()
    {
        Intent intent = new Intent(this, Subject.class);
        startActivity(intent);
    }
    public void prepapers()
    {
        Intent intent = new Intent(this, previousyrsubject.class);
        startActivity(intent);
    }
    public void subscription()
    {
        Intent intent = new Intent(this, paidnote.class);
        startActivity(intent);
    }
    public void mocktest()
    {
        dbquery.loadData(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(HomepageActivity.this, Mocktestsub.class);
                startActivity(intent);

            }
            @Override
            public void onFailure()
            {
                Toast.makeText(HomepageActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void video()
    {
        Intent intent = new Intent(this, videosubject.class);
        startActivity(intent);
    }
    public void openhome()
    {
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
    }
    public void opendashboard()
    {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
    public void myAccount()
    {
        Intent intent = new Intent(this, MyAccount.class);
        startActivity(intent);

    }

}
