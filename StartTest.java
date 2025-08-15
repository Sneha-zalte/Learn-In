package com.example.mocktest_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.Activities.Questions;

public class StartTest extends AppCompatActivity {

    TextView stCatname,sttestno,sttotalque,stbestscore,sttime;

    Button startbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_test);

        init();
        dbquery.loadQuestions(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                setData();
            }

            @Override
            public void onFailure() {
                Toast.makeText(StartTest.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void init()
    {
        stCatname = findViewById(R.id.stcatname);
        sttestno = findViewById(R.id.stTestno);
        sttotalque = findViewById(R.id.stnoofque);
        stbestscore = findViewById(R.id.stbestscore);
        sttime = findViewById(R.id.sttesttime);
        startbtn= findViewById(R.id.startTest);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartTest.this, Questions.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setData()
    {
        stCatname.setText(dbquery.gcatList.get(dbquery.gselectedcat_id).getName());
        sttestno.setText("Test No." + String.valueOf(dbquery.gselectedtest_id+1));
        sttotalque.setText(String.valueOf(dbquery.gQuestionList.size()));
        stbestscore.setText(String.valueOf(dbquery.gtestList.get(dbquery.gselectedtest_id).getTopScore()));
        sttime.setText(String.valueOf(dbquery.gtestList.get(dbquery.gselectedtest_id).getTime()));
    }
}