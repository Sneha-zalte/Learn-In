package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    ImageView logo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.logoanim);
        logo.setAnimation(anim);

        mAuth = FirebaseAuth.getInstance();
        dbquery.gFirestore= FirebaseFirestore.getInstance();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (mAuth.getCurrentUser() != null)
                {
                    Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                Intent intent = new Intent(MainActivity.this, LoginpageActivity.class);
                startActivity(intent);
                finish();
                }
            }
        },3000);

    }
}






