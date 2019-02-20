package com.example.contentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WebView extends AppCompatActivity {


android.webkit.WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);

         webView= findViewById(R.id.webView);
        webView.loadUrl("https://www.google.com/");

    }
}
