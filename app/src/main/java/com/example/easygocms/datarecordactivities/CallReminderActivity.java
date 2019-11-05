package com.example.easygocms.datarecordactivities;

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

public class CallReminderActivity extends AppCompatActivity {

    WebView webViewCallReminder;
    SharedPreferences sharedPrefLogin;
    String URL="http://easygocms.com/autodialerapp/call_reminder.php?adminid=";
    ImageView imageViewhome;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_reminder);

        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" +sharedPrefLogin.getString("user_id","");
        webViewCallReminder=findViewById(R.id.webviewcallreminder);
        webViewCallReminder.setWebViewClient(new WebViewClient());
        webViewCallReminder.getSettings().setJavaScriptEnabled(true);
        webViewCallReminder.loadUrl(URL);
        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CallReminderActivity.this, HomeScreenActivity.class);
                startActivity(intent
                );
            }
        });

    }
}
