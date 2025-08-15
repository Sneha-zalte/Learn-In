package com.example.mocktest_app.SubjectsPage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.Activities.HomepageActivity;
import com.example.mocktest_app.Activities.LeaderboardActivity;
import com.example.mocktest_app.R;
import com.example.mocktest_app.VideoLecture.Cvideo;

public class videosubject extends AppCompatActivity {
    TextView c,html,office,dbms,math,cplus,coa,java,dsa,micro,se,python,networking,cybersecurity,ai,android,multimedia,mis,dw,cloud;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videosubject);

        c = findViewById(R.id.C);
        html = findViewById(R.id.html);
        office = findViewById(R.id.office);
        dbms = findViewById(R.id.dbms);
        math = findViewById(R.id.maths);
        cplus = findViewById(R.id.cplus);
        coa = findViewById(R.id.coa);
        java = findViewById(R.id.java);
        dsa = findViewById(R.id.dsa);
        micro = findViewById(R.id.micro);
        se = findViewById(R.id.se);
        python = findViewById(R.id.python);
        networking = findViewById(R.id.networking);
        cybersecurity = findViewById(R.id.cybersecurity);
        ai = findViewById(R.id.ai);
        android = findViewById(R.id.android);
        multimedia = findViewById(R.id.multimedia);
        mis = findViewById(R.id.mis);
        dw = findViewById(R.id.datawarehouse);
        cloud = findViewById(R.id.cloud);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });

        html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);

            }
        });

        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);

            }
        });

        dbms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);

            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        cplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        coa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        java.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        dsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        micro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        python.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        networking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        cybersecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        multimedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        mis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        dw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
            }
        });
        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cvideo.class);
                startActivity(intent);
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
        Intent intent = new Intent(this, HomepageActivity.class);
        startActivity(intent);
    }

    public void opendashboard()
    {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    public void Logout()
    {

    }
}