package com.example.easygocms.datacallingreport;

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

public class RingingActivity extends AppCompatActivity {

    ImageView imageViewhome;
    WebView webViewRinging;
    String URL="http://easygocms.com/autodialerapp/ringing_report.php?adminid=";
    SharedPreferences sharedPrefLogin;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringing);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");
        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RingingActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
        webViewRinging=findViewById(R.id.webviewringing);
        webViewRinging.setWebViewClient(new WebViewClient());
        webViewRinging.getSettings().setJavaScriptEnabled(true);
        webViewRinging.loadUrl(URL);

    }
}
