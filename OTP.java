package com.example.mocktest_app.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mocktest_app.R;

public class OTP extends AppCompatActivity {
    EditText number;
    Button sms;
    TextView resend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        number = (EditText) findViewById(R.id.mob);
        sms = (Button) findViewById(R.id.send);
        resend = (TextView) findViewById(R.id.resend);

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String phone = number.getText().toString();
                if (!phone.isEmpty())
                {
                    sendSMS();
                }

            }
        });


        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(OTP.this, Manifest.permission.SEND_SMS)
                ==PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }
                else
                {
                    ActivityCompat.requestPermissions(OTP.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
         sendSMS();
        }
        else
        {
            Toast.makeText(this,"Permission denied",Toast.LENGTH_LONG).show();
        }
    }

    public void sendSMS()
    {
        String phone = number.getText().toString();
        if (!phone.isEmpty())
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,"123456 is your mobile verification OTP",null,null);
            Toast.makeText(this,"OTP sent Successfully",Toast.LENGTH_LONG).show();
            openverify();
        }
        else
        {
            Toast.makeText(this,"enter mobile no.",Toast.LENGTH_LONG).show();
        }
    }

    public void openverify()
    {
        Intent intent = new Intent(this, Verify.class);
        startActivity(intent);
    }


}