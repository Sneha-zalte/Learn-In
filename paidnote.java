package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.R;
import com.example.mocktest_app.Razorpay;

public class paidnote extends AppCompatActivity {

    Button paid1,paid2,paid3,paid4,paid5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paidnote);

        paid1 = findViewById(R.id.paid1);
        paid2 = findViewById(R.id.paid2);
        paid3 = findViewById(R.id.paid3);
        paid4 = findViewById(R.id.paid4);
        paid5 = findViewById(R.id.paid5);

        paid1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Razorpay.class);
                startActivity(intent);

            }
        });
        paid2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        paid3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        paid4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        paid5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}