package com.example.mocktest_app.Activities;

import static com.example.mocktest_app.dbquery.gUserList;
import static com.example.mocktest_app.dbquery.gusersCount;
import static com.example.mocktest_app.dbquery.myperformance;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;
import com.google.firebase.auth.FirebaseAuth;

public class MyAccount extends AppCompatActivity {

    private Dialog progressdialog;
    private TextView dialogtxt;

    LinearLayout bookmarkB,leaderB,profileB,LogoutB;
    TextView profileimgtxt,name,Oscore,rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        progressdialog = new Dialog(MyAccount.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Loading...");


        initViews();

        String Uname = dbquery.myprofile.getName();
        profileimgtxt.setText(Uname.toUpperCase().substring(0,1));

        name.setText(Uname);

        Oscore.setText(String.valueOf(dbquery.myperformance.getScore()));
        rank.setText(String.valueOf(myperformance.getRank()));

        if (dbquery.gUserList.size() == 0)
        {
            progressdialog.show();
            dbquery.getTopUsers(new MyCompleteListener() {
                @Override
                public void onSuccess() {
                    if (dbquery.myperformance.getScore() != 0)
                    {
                        if (! dbquery.isMeOnToplist)
                        {
                            calculateRank();
                        }
                        Oscore.setText("Score"+ dbquery.myperformance.getScore());
                        rank.setText("Rank" + dbquery.myperformance.getRank());
                    }

                    progressdialog.dismiss();
                }

                @Override
                public void onFailure() {

                    Toast.makeText(MyAccount.this, "something went wrong", Toast.LENGTH_SHORT).show();

                    progressdialog.dismiss();

                }
            });
        }
        else
        {
            Oscore.setText("Score :"+ dbquery.myperformance.getScore());
            if (myperformance.getScore() != 0)
            {
                rank.setText("Rank -" + dbquery.myperformance.getRank());
            }

        }






        bookmarkB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, BookmarkActivity.class);
                startActivity(intent);

            }
        });

        leaderB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyAccount.this, LeaderboardActivity.class);
                startActivity(intent);

            }
        });

        profileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, UserAccount.class);
                startActivity(intent);

            }
        });

        LogoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutUser();
            }
        });

    }

    private void initViews()
    {
        bookmarkB = findViewById(R.id.savedQuestions);
        leaderB = findViewById(R.id.Leaderb);
        profileB = findViewById(R.id.userac);
        LogoutB = findViewById(R.id.Logout);
        profileimgtxt = findViewById(R.id.userprofile);
        name = findViewById(R.id.userName);
        Oscore = findViewById(R.id.userScore);
        rank = findViewById(R.id.rank);
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
    private void LogoutUser()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MyAccount.this, LoginpageActivity.class);
        startActivity(intent);
    }




}