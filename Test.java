package com.example.mocktest_app.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Adapters.testadaptor;
import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;

public class Test extends AppCompatActivity {

    private RecyclerView testView;
    private testadaptor adaptor;
    private Toolbar toolbar;
    private Dialog progressdialog;
    private TextView dialogtxt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testView = findViewById(R.id.recycle);

        progressdialog = new Dialog(Test.this);
        progressdialog.setContentView(R.layout.progressbar);
        progressdialog.setCancelable(false);
        progressdialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogtxt = progressdialog.findViewById(R.id.dialogtxt);
        dialogtxt.setText("Loading...");
        progressdialog.show();

        toolbar= findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle(dbquery.gcatList.get(dbquery.gselectedcat_id).getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        testView.setLayoutManager(layoutManager);


        dbquery.loadTestdata(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                adaptor = new testadaptor(dbquery.gtestList);
                testView.setAdapter(adaptor);
                progressdialog.dismiss();
            }
            @Override
            public void onFailure() {
                Toast.makeText(Test.this, "Something went wrong",Toast.LENGTH_SHORT).show();
                progressdialog.dismiss();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            Test.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}