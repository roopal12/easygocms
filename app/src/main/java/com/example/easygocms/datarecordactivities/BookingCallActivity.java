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

public class BookingCallActivity extends AppCompatActivity {

      WebView webViewbookingcall;
      SharedPreferences sharedPrefLogin;
      String URL=" http://easygocms.com/autodialerapp/booking_call.php?adminid=";
    ImageView imageViewhome;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingcall);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" +sharedPrefLogin.getString("user_id","");
        webViewbookingcall=findViewById(R.id.webviewbookingcall);
        webViewbookingcall.setWebViewClient(new WebViewClient());
        webViewbookingcall.getSettings().setJavaScriptEnabled(true);
        webViewbookingcall.loadUrl(URL);
        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookingCallActivity.this, HomeScreenActivity.class);
                startActivity(intent
                );
            }
        });
    }
}
