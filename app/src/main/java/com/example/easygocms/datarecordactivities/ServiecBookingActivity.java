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


public class ServiecBookingActivity extends AppCompatActivity {

    ImageView Imageview;
    WebView webviewsservicebooking;
    String URL="http://easygocms.com/autodialerapp/service_booking.php?adminid=";
    SharedPreferences sharedPrefLogin;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviec_booking);

        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");

        Imageview=findViewById(R.id.backtohomescreen);
        Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ServiecBookingActivity.this, HomeScreenActivity.class);
                startActivity(intent);
            }
        });

        webviewsservicebooking=findViewById(R.id.webviewsservicebooking);
        webviewsservicebooking.setWebViewClient(new WebViewClient());
        webviewsservicebooking.getSettings().setJavaScriptEnabled(true);
        webviewsservicebooking.loadUrl(URL);

    }
}
