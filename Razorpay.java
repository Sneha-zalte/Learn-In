package com.example.mocktest_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class Razorpay extends AppCompatActivity implements PaymentResultListener {
    Button Payment;
    TextView Paymentstatus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_razorpay);

        Checkout.preload(getApplicationContext());

        Payment = findViewById(R.id.paybtn);
        Paymentstatus = findViewById(R.id.paystatus);

        Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PaymentNow("1000");

            }
        });
    }

    private void PaymentNow(String amount)
    {
        final Activity activity = this;

        Checkout checkout= new Checkout();
        checkout.setKeyID("rzp_test_8tZXu3uXT1kgh");
        checkout.setImage(R.drawable.ic_launcher_background);

        double finalAmount = Float.parseFloat(amount)*100;

        try {
            JSONObject options = new JSONObject();
            options.put("name","Learn-in");
            options.put("theme.color","#3399cc");
            options.put("description","Ref No. #123456");
            options.put("currency","INR");
            options.put("amount",finalAmount+"");
            options.put("prefill.email","snehazalte005@gmail.com");
            options.put("prefill.contact","8657684375");

            checkout.open(activity,options);
        } catch (JSONException e) {
            Log.e("TAG","Error in starting Razorpay checkout");
        }


    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show();
        Paymentstatus.setText(s);
    }

    @Override
    public void onPaymentError(int i, String s) {


        Paymentstatus.setText(s);
        Toast.makeText(this, "Payment Mode Currently Unavailable", Toast.LENGTH_SHORT).show();

    }
}