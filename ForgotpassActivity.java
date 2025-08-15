package com.example.mocktest_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpassActivity extends AppCompatActivity {

    Button reset,back;
    EditText femail;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        reset = findViewById(R.id.resetbtn);
        back = findViewById(R.id.backbtn);
        femail = findViewById(R.id.femail);
        progressBar = findViewById(R.id.fprogress);

        mAuth = FirebaseAuth.getInstance();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                strEmail = femail.getText().toString().trim();
                if (!TextUtils.isEmpty(strEmail))
                {
                    ResetPass();
                }
                else
                {
                    femail.setError("Email field can't be empty");
                }

            }
        });

    }

    private void ResetPass()
    {
        progressBar.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);

        mAuth.sendPasswordResetEmail(strEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused)
            {
                Toast.makeText(ForgotpassActivity.this,"Reset Password link has been send to your Email",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForgotpassActivity.this, LoginpageActivity.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(ForgotpassActivity.this, "Error"+ e.getMessage(),
                                Toast.LENGTH_SHORT).show();

                        progressBar.setVisibility(View.INVISIBLE);
                        reset.setVisibility(View.VISIBLE);

                    }
                });
    }
}