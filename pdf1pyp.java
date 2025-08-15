package com.example.mocktest_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class pdf1pyp extends AppCompatActivity {
    PDFView pdfView;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf3);
        pdfView = (PDFView) findViewById(R.id.pdfviewtxt);
        pdfView.fromAsset("C-pyp.pdf").load();
    }
}