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

public class NotifLostCustomerActivity extends AppCompatActivity {
     WebView webviewnotircLOSTCUSTOMER;

    SharedPreferences sharedPrefLogin;

    String URL = "http://easygocms.com/autodialerapp/lost_call_reminder.php?adminid=";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_lost_customer);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" +sharedPrefLogin.getString("user_id","");


        ImageView imageView = findViewById(R.id.backtohomescreen);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotifLostCustomerActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        webviewnotircLOSTCUSTOMER = findViewById(R.id.webviewnotircLOSTCUSTOMER);
        webviewnotircLOSTCUSTOMER.setWebViewClient(new WebViewClient());
        webviewnotircLOSTCUSTOMER.getSettings().setJavaScriptEnabled(true);
        webviewnotircLOSTCUSTOMER.loadUrl(URL);
        System.out.println("ServiecReminderUrl" + URL);
    }
}
