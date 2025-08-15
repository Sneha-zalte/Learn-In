package com.example.mocktest_app;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.Activities.AnswerActivity;
import com.example.mocktest_app.Activities.LeaderboardActivity;
import com.example.mocktest_app.Models.Questionmodel;

import java.util.concurrent.TimeUnit;

public class ScoreActivity extends AppCompatActivity {

    TextView Scoretxt,Timetxt,Totalquetxt,correctquetxt,wrongquetxt,unattemptquetxt;
    Button leaderboard,reattempt,viewans;
    long timetaken;
    Dialog progressdialog;
    TextView dialogtxt;
    int finalscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        /*Toolbar toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        progressdialog = new Dialog(ScoreActivity.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Loading...");
        progressdialog.show();

        init();
        loadData();
        setBookmark();
        saveResults();

        viewans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ScoreActivity.this, AnswerActivity.class);
                startActivity(intent);

            }
        });

        reattempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                reattemptf();
            }
        });

        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init()
    {
        Scoretxt = findViewById(R.id.score);
        Timetxt = findViewById(R.id.timetaken);
        Totalquetxt = findViewById(R.id.totalque);
        correctquetxt = findViewById(R.id.rightans);
        wrongquetxt = findViewById(R.id.wrongans);
        unattemptquetxt = findViewById(R.id.unattempt);
        leaderboard = findViewById(R.id.leaderboard);
        reattempt = findViewById(R.id.reattempt);
        viewans = findViewById(R.id.viewanswer);
    }

    private void loadData()
    {
        int correctQ = 0 , wrongQ = 0 , unattempt = 0 ;

        for (int i = 0 ; i < dbquery.gQuestionList.size(); i++)
        {
            if (dbquery.gQuestionList.get(i).getSelectedAns() == -1)
            {
                unattempt++;
            }
            else
            {
                if (dbquery.gQuestionList.get(i).getSelectedAns() == dbquery.gQuestionList.get(i).getCorrectAns())
                {
                    correctQ++ ;
                }

                else
                {
                    wrongQ++ ;
                }
            }
        }

        correctquetxt.setText(String.valueOf(correctQ));
        wrongquetxt.setText(String.valueOf(wrongQ));
        unattemptquetxt.setText(String.valueOf(unattempt));

        Totalquetxt.setText(String.valueOf(dbquery.gQuestionList.size()));

        finalscore = (correctQ*100)/dbquery.gQuestionList.size();
        Scoretxt.setText(String.valueOf(finalscore));

        timetaken = getIntent().getLongExtra("TIME_TAKEN",0);
        String time = String.format("%02d : %02d min",
                TimeUnit.MILLISECONDS.toMinutes(timetaken),
                TimeUnit.MILLISECONDS.toSeconds(timetaken)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timetaken)));

        Timetxt.setText(time);


    }

    private void setBookmark()
    {
        for (int i=0; i < dbquery.gQuestionList.size(); i++)
        {
            Questionmodel questionmodel = dbquery.gQuestionList.get(i);
            if (questionmodel.isBookmarked())
            {
               if ( ! dbquery.gBookmarklist.contains(questionmodel.getqId()))
               {
                   dbquery.gBookmarklist.add(questionmodel.getqId());
                   dbquery.myprofile.setBookmarkCount(dbquery.gBookmarklist.size());
               }
            }
            else
            {
                if (dbquery.gBookmarklist.contains(questionmodel.getqId()))
                {
                    dbquery.gBookmarklist.remove(questionmodel.getqId());
                    dbquery.myprofile.setBookmarkCount(dbquery.gBookmarklist.size());
                }

            }
        }

    }
    private void reattemptf()
    {
        for (int i = 0 ; i< dbquery.gQuestionList.size(); i++)
        {
            dbquery.gQuestionList.get(i).setSelectedAns(-1);
            dbquery.gQuestionList.get(i).setStatus(dbquery.NOT_VISITED);
        }

        Intent intent = new Intent(ScoreActivity.this, StartTest.class);
        startActivity(intent);
        finish();
    }
    private void saveResults()
    {
        dbquery.saveResult(finalscore, new MyCompleteListener() {
            @Override
            public void onSuccess() {
                progressdialog.dismiss();
            }

            @Override
            public void onFailure() {
                progressdialog.dismiss();
                Toast.makeText(ScoreActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            ScoreActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}