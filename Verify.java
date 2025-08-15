package com.example.mocktest_app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.R;

public class Verify extends AppCompatActivity {

    EditText otp ;
    Button verify;
    TextView resend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        otp= (EditText) findViewById(R.id.OTP);
        verify= (Button) findViewById(R.id.send);
        resend = (TextView) findViewById(R.id.resend);


        verify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String OTP = otp.getText().toString();


                if (TextUtils.isEmpty(OTP))
                {
                    Toast.makeText(Verify.this, "please enter the detail",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Userprofile();
                }

            }
        });
    }

    public void Userprofile()
    {
        String OTP = otp.getText().toString();
        if (OTP.equals("123456"))
        {
            Toast.makeText(this, "Mobile No. verified successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, HomepageActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"OTP is not valid",Toast.LENGTH_LONG).show();
        }
    }
}