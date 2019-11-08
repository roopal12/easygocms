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

public class PSFSummaryActivity extends AppCompatActivity {
    WebView webViewPsfSummary;
    ImageView imageViewhome;
    SharedPreferences sharedPrefLogin;
    String URL="http://easygocms.com/autodialerapp/user_psfsummary_report.php?adminid=";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psfsummary);
        sharedPrefLogin = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        URL = URL + sharedPrefLogin.getString("admin_id", "")+"&userid=" + sharedPrefLogin.getString("user_id","");
        webViewPsfSummary=findViewById(R.id.webviewpsfsummary);
        webViewPsfSummary.setWebViewClient(new WebViewClient());
        webViewPsfSummary.getSettings().setJavaScriptEnabled(true);
        webViewPsfSummary.loadUrl(URL);
        imageViewhome=findViewById(R.id.backtohomescreen);
        imageViewhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PSFSummaryActivity.this, HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
