package com.example.mocktest_app.Activities;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class signuppg extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String emailStr;
    private String PassStr;
    private String NameStr;
    private Dialog progressdialog;
    FirebaseDatabase database;
    FirebaseFirestore gfirestore;
    String UserId;
    TextView lg;
    EditText email,user,password,confirmpass;
    Button register;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppg);

        lg = findViewById(R.id.sign);
        email = findViewById(R.id.email);
        user = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        register = findViewById(R.id.regbtn);
        confirmpass = findViewById(R.id.confirmpass);



        progressdialog = new Dialog(signuppg.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Registering user...");
        mAuth = FirebaseAuth.getInstance();
        gfirestore = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (validate()) {
                    SignupNewuser();
                }

            }
        });

        lg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openlg();
            }
        });
    }

    private boolean validate()
    {
        NameStr = user.getText().toString().trim();
        PassStr = password.getText().toString().trim();
        String confirmPassStr = confirmpass.getText().toString().trim();
        emailStr = email.getText().toString().trim();
    
        if (emailStr.isEmpty())
        {
            email.setError("Enter you emailaddress");
            return false;
        }

        if (NameStr.isEmpty())
        {
            user.setError("Enter you name");
            return false;
        }

        if (PassStr.isEmpty())
        {
            password.setError("Enter you password");
            return false;
        }

        if (confirmPassStr.isEmpty())
        {
            confirmpass.setError("Enter you confirmed password");
            return false;
        }

        if (PassStr.compareTo(confirmPassStr)!= 0)
        {
            Toast.makeText(signuppg.this,"Password didnot match!!",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void SignupNewuser()
    {
        progressdialog.show();
        mAuth.createUserWithEmailAndPassword(emailStr, PassStr)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(signuppg.this,"SignupSuccessful",Toast.LENGTH_LONG).show();
                        progressdialog.dismiss();
                        Intent intent = new Intent(signuppg.this, LoginpageActivity.class);
                        startActivity(intent);
                        signuppg.this.finish();
                        dbquery.createuserdata(emailStr,NameStr, new MyCompleteListener()
                        {
                           @Override
                           public void onSuccess()
                           {
                               Toast.makeText(signuppg.this,"data added",Toast.LENGTH_LONG).show();
                           }
                           @Override
                           public void onFailure()
                           {
                               Toast.makeText(signuppg.this,"something went wrong while adding data",Toast.LENGTH_SHORT).show();
                               progressdialog.dismiss();
                           }});
                    }
                    else
                    {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(signuppg.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        progressdialog.dismiss();
                    }
                });
    }
    public void openlg()
    {
        Intent intent = new Intent(this, LoginpageActivity.class);
        startActivity(intent);
    }

}











