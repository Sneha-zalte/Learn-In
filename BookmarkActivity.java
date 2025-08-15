package com.example.mocktest_app.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mocktest_app.Adapters.BookmarkAdaptor;
import com.example.mocktest_app.MyCompleteListener;
import com.example.mocktest_app.R;
import com.example.mocktest_app.dbquery;

public class BookmarkActivity extends AppCompatActivity {
    private RecyclerView bookmarkview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        bookmarkview = findViewById(R.id.bookmarkview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        bookmarkview.setLayoutManager(layoutManager);

        dbquery.Bookmarks(new MyCompleteListener() {
            @Override
            public void onSuccess()
            {
                BookmarkAdaptor adaptor = new BookmarkAdaptor(dbquery.gBookmrkqueList);
                bookmarkview.setAdapter(adaptor);
            }
            @Override
            public void onFailure() {
                Toast.makeText(BookmarkActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
        {
            BookmarkActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}