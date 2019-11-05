package com.example.easygocms.datacontrolactivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easygocms.R;
import com.example.easygocms.activities.HomeScreenActivity;

public class DataAlotActivity extends AppCompatActivity {
    ImageView Imageview;
    WebView webviewdataallot;
    String URL="http://easygocms.com/autodialerapp/data_allot.php?adminid=";

    SharedPreferences sharedPrefLogin;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_alot);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");
        Imageview=findViewById(R.id.backtohomescreen);
        Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DataAlotActivity.this, HomeScreenActivity.class);
                startActivity(intent);

            }
        });
        webviewdataallot=findViewById(R.id.webviewdataallot);
        webviewdataallot.setWebViewClient(new WebViewClient());
        webviewdataallot.getSettings().setJavaScriptEnabled(true);
        webviewdataallot.loadUrl(URL);

    }
}
