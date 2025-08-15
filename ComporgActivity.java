package com.example.mocktest_app;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class ComporgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comporg);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        VideoView videoView = (VideoView) findViewById(R.id.videoView2);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.computerorg));

        // new set media controller

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}