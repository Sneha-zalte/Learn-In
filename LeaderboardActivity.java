package com.example.mocktest_app.Activities;

import static com.example.mocktest_app.dbquery.gUserList;
import static com.example.mocktest_app.dbquery.gusersCount;
import static com.example.mocktest_app.dbquery.myperformance;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Adapters.RankAdapter;
import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LeaderboardActivity extends AppCompatActivity {

    TextView totaluserTV,myimgtextTV,myscoreTV,myrankTV;
    RecyclerView userView;
    RankAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseFirestore gFirestore;

    private Dialog progressdialog;
    private TextView dialogtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        progressdialog = new Dialog(LeaderboardActivity.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Loading...");
        progressdialog.show();

        mAuth = FirebaseAuth.getInstance();
        gFirestore = FirebaseFirestore.getInstance();

        init();

        myscoreTV.setText("Score :"+ dbquery.myperformance.getScore());
        myrankTV.setText("Rank- " + dbquery.myperformance.getRank());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        userView.setLayoutManager(layoutManager);

        adapter = new RankAdapter(gUserList);
        userView.setAdapter(adapter);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View home = findViewById(R.id.navigation_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhome();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View dash = findViewById(R.id.navigation_dashboard);
        dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendashboard();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        View myaccount = findViewById(R.id.navigation_notifications);
        myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAccount();
            }
        });

        dbquery.getTopUsers(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                adapter.notifyDataSetChanged();
                if (dbquery.myperformance.getScore() != 0)
                {
                    if (! dbquery.isMeOnToplist)
                    {
                        calculateRank();
                    }
                    myscoreTV.setText("Score :"+ dbquery.myperformance.getScore());
                    myrankTV.setText("Rank -" + dbquery.myperformance.getRank());
                }
                progressdialog.dismiss();
            }

            @Override
            public void onFailure() {

                Toast.makeText(LeaderboardActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                progressdialog.dismiss();

            }
        });

        totaluserTV.setText("Total Users:" + dbquery.gusersCount);
        myimgtextTV.setText(myperformance.getName().toUpperCase().substring(0,1));


    }

    private void init()
    {
        totaluserTV = findViewById(R.id.totalusers);
        myimgtextTV = findViewById(R.id.imgtxt);
        myscoreTV = findViewById(R.id.Score);
        myrankTV = findViewById(R.id.rank);
        userView = findViewById(R.id.leaderview);
    }

    private void calculateRank()
    {
        int lowtopscore = gUserList.get(gUserList.size()-1).getScore();

        int remaining_slot = gusersCount - 20 ;

        int myslot = myperformance.getScore()*remaining_slot/lowtopscore;

        int rank;

        if (lowtopscore != myperformance.getScore())
        {
            rank = gusersCount-myslot;
        }
        else
        {
            rank = 21;
        }
        myperformance.setRank(rank);

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