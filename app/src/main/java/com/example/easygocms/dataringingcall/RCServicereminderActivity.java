package com.example.easygocms.dataringingcall;

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

public class RCServicereminderActivity extends AppCompatActivity {

     WebView webviewrcservrem;
     String URL="  http://easygocms.com/autodialerapp/ringing_service_reminder.php?adminid=";
     SharedPreferences sharedPrefLogin;
     ImageView imageView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcservicereminder);
        webviewrcservrem=findViewById(R.id.webviewrcservrem);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");
        webviewrcservrem.setWebViewClient(new WebViewClient());
        webviewrcservrem.getSettings().setJavaScriptEnabled(true);
        webviewrcservrem.loadUrl(URL);
        imageView=findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RCServicereminderActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
