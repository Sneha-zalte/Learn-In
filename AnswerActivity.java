package com.example.mocktest_app.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Adapters.AnswerAdaptor;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;

public class AnswerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        RecyclerView answerview = findViewById(R.id.answerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        answerview.setLayoutManager(layoutManager);

        AnswerAdaptor adaptor = new AnswerAdaptor(dbquery.gQuestionList);
        answerview.setAdapter(adaptor);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            AnswerActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}