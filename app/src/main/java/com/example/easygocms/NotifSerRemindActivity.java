package com.example.easygocms;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.easygocms.activities.HomeScreenActivity;
import com.example.easygocms.datarecordactivities.ServiecReminderActivity;

public class NotifSerRemindActivity extends AppCompatActivity {
    WebView webviewnotircservrem ;

    SharedPreferences sharedPrefLogin;

    String URL = " http://easygocms.com/autodialerapp/auto_call_reminder.php?adminid=";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_ser_remind);

        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" +sharedPrefLogin.getString("user_id","");


        ImageView imageView = findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifSerRemindActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        webviewnotircservrem = findViewById(R.id.webviewnotircservrem);
        webviewnotircservrem.setWebViewClient(new WebViewClient());
        webviewnotircservrem.getSettings().setJavaScriptEnabled(true);
        webviewnotircservrem.loadUrl(URL);
        System.out.println("ServiecReminderUrl" + URL);

    }
}
