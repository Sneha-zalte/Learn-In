package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;

public class UserAccount extends AppCompatActivity {

    TextView profileimgtxt;
    EditText name,email,phone;
    LinearLayout EditB,Btnlayout;
    Button cancelB,saveB;

    String namestr,phonestr;

    private Dialog progressdialog;
    private TextView dialogtxt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        profileimgtxt = findViewById(R.id.userprofile);
        name = findViewById(R.id.acuname);
        email = findViewById(R.id.acuemail);
        phone = findViewById(R.id.acuphone);
        EditB = findViewById(R.id.EditB);
        cancelB = findViewById(R.id.cancelb);
        saveB = findViewById(R.id.saveb);
        Btnlayout = findViewById(R.id.btnlayout);

        progressdialog = new Dialog(UserAccount.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Loading...");

        disableEditing();

        EditB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enableEditing();

            }
        });

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableEditing();
            }
        });

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate())
                {
                    saveData();
                }
            }
        });
    }

    private void disableEditing()
    {
        name.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        Btnlayout.setVisibility(View.GONE);

        name.setText(dbquery.myprofile.getName());
        email.setText(dbquery.myprofile.getEmail());

        if (dbquery.myprofile.getPhone() != null)
        {
            phone.setText(dbquery.myprofile.getPhone());
        }

        String profilename = dbquery.myprofile.getName();
        profileimgtxt.setText(profilename.toUpperCase().substring(0,1));
    }

    private void enableEditing()
    {
        name.setEnabled(true);
        //email.setEnabled(true);
        phone.setEnabled(true);
        Btnlayout.setVisibility(View.VISIBLE);
    }

    private boolean validate()
    {
        namestr = name.getText().toString();
        phonestr = phone.getText().toString();


        if (namestr.isEmpty())
        {
            name.setError("Name can not br empty !");
            return false;
        }

        if (!phonestr.isEmpty())
        {
            if(! ((phonestr.length() == 10) && (TextUtils.isDigitsOnly(phonestr))))
            {
                phone.setError("Enter valid phone Number !");
                return false;
            }
        }

        return true;
    }

    private void saveData()
    {
        progressdialog.show();

        if (phonestr.isEmpty())
        {
            phonestr = null;
        }
        dbquery.saveProfileData(namestr, phonestr, new MyCompleteListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(UserAccount.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                disableEditing();
                progressdialog.dismiss();

            }

            @Override
            public void onFailure() {
                Toast.makeText(UserAccount.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                progressdialog.dismiss();

            }
        });

    }

}